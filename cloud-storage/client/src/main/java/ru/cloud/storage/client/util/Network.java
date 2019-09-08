package ru.cloud.storage.client.util;

import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import javafx.application.Platform;
import ru.cloud.storage.client.controller.MainController;
import ru.cloud.storage.client.entity.StoragePath;
import ru.cloud.storage.common.Commands;
import ru.cloud.storage.common.message.CmdMsg;
import ru.cloud.storage.common.message.FileListMsg;
import ru.cloud.storage.common.message.FileMsg;
import ru.cloud.storage.common.message.ReplyMsg;
import ru.cloud.storage.common.util.MD5Checksum;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Network {
    private static Network ourInstance = new Network();

    public static Network getInstance() {
        return ourInstance;
    }

    private Socket socket;
    private ObjectEncoderOutputStream out;
    private ObjectDecoderInputStream in;
    private List<Path> fileListServer;
    private MainController mainController;
    private String filename;
    private Path dstFolder;
    private Path dstFile;
    private long fileSize;
    private int bufferSize;
    private boolean flag;
    private OutputStream os;
    private String crc;

    public List<Path> getFileListServer() {
        return fileListServer;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private boolean isConnected() {
        return socket != null && !socket.isClosed();
    }

    public void checkConnected() {
        if (!this.isConnected())
            this.connect();
    }

    private Network() {
    }

    private void connect() {
        try {
            String serverIp = Assets.getInstance().getServerIp();
            int serverPort = Assets.getInstance().getServerPort();

            this.socket = new Socket(serverIp, serverPort);
            this.in = new ObjectDecoderInputStream(socket.getInputStream());
            this.out = new ObjectEncoderOutputStream(socket.getOutputStream());
            this.bufferSize = Assets.getInstance().getBufferSize();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(() -> {
            while (true) {
                Object msg = this.readObject();

                if (msg instanceof FileMsg && !flag) {
                    FileMsg fileMsg = (FileMsg) msg;
                    this.filename = fileMsg.getFilename();
                    this.dstFolder = Paths.get(fileMsg.getDestination());
                    this.dstFile = Paths.get(dstFolder.toString(), filename);
                    this.fileSize = fileMsg.getSize();
                    this.bufferSize = fileMsg.getBufferSize();
                    this.crc = fileMsg.getCrc();
                    this.flag = true;
                    try {
                        if (Files.exists(dstFile))
                            Files.deleteIfExists(dstFile);
                        Files.createFile(dstFile);
                        os = Files.newOutputStream(dstFile, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (flag && msg instanceof byte[]) {
                    byte[] byteArray = (byte[]) msg;
                    try {
                        if (fileSize > bufferSize) {
                            os.write(byteArray);
                            os.flush();
                            fileSize -= byteArray.length;
                        } else {
                            byte[] lastPart = Arrays.copyOf(byteArray, (int) fileSize);
                            os.write(lastPart);
                            os.flush();
                            fileSize -= lastPart.length;
                        }

                        if (fileSize <= 0) {
                            this.flag = false;
                            String newCrc = MD5Checksum.getMD5Checksum(dstFile);
                            if (!crc.equals(newCrc)) {
                                Files.deleteIfExists(dstFile);
                                WindowManager.showErrorAlert("Received file is damaged!");
                            }
                            mainController.refillLocalTable();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (msg instanceof CmdMsg) {
                    CmdMsg cmdMsg = (CmdMsg) msg;
                    if (cmdMsg.equals(Commands.CREATEDIR)) {
                        Path target = Paths.get((String) cmdMsg.getAttachment()[0]);
                        try {
                            Files.createDirectory(target);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (msg instanceof FileListMsg) {
                    this.fileListHandler((FileListMsg) msg);
                }

                if (msg instanceof ReplyMsg) {
                    this.replyMessageHandler((ReplyMsg) msg);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void sendObject(Object object) {
        this.checkConnected();
        try {
            out.writeObject(object);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(FileMsg fileMsg, int bufferSize) {
        fileMsg.setBufferSize(bufferSize);

        Thread sendFileThread = new Thread(() -> {
            this.sendObject(fileMsg);

            byte[] byteArray = new byte[bufferSize];
            long fileSize = fileMsg.getSize();

            WindowManager.showProgress(true);
            InputStream fis = null;
            try {
                fis = Files.newInputStream(Paths.get(fileMsg.getSource()));
                int maxPackets = (int)(fileSize / bufferSize);
                int curPacket = 0;
                while (fileSize > 0) {
                    curPacket++;
                    int i = fis.read(byteArray);
                    out.writeObject(byteArray);
                    out.flush();
                    fileSize -= i;
                    int curProgress = curPacket * 100 / maxPackets;
                    Assets.getInstance().getProgressController().setProgress(curProgress);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    Platform.runLater(() -> WindowManager.getProgressStage().close());
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        sendFileThread.start();
    }

    public void sendFile(Path src, Path dst, int bufferSize) {
        FileMsg fileMsg = new FileMsg(src, dst, true);
        this.sendFile(fileMsg, bufferSize);
    }

    private Object readObject() {
        this.checkConnected();
        try {
            return in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    void disconnect() {
        if (isConnected()) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void fileListHandler(FileListMsg msg) {
        this.fileListServer = msg.getFiles().stream()
                .map(path -> Paths.get(path))
                .collect(Collectors.toList());
        Path path = Paths.get(msg.getPath());
        StoragePath sp = Assets.getInstance().getServerPath();
        sp.setFullPath(path);
        if (sp.getRoot() == null) {
            sp.setRoot(path);
        }
        if (mainController != null)
            mainController.refillServerTable();
    }

    private void replyMessageHandler(ReplyMsg repMsg) {
        if (repMsg.equals(Commands.UPLOADFAIL))
            WindowManager.showErrorAlert("Error during uploading file.");

        if (repMsg.equals(Commands.LOGOUTOK))
            WindowManager.showLogin();

        if (repMsg.equals(Commands.AUTHOK))
            WindowManager.showMain();

        if (repMsg.equals(Commands.AUTHWRONG))
            WindowManager.showErrorAlert("Wrong login or password.");

        if (repMsg.equals(Commands.LOGINBUSY))
            WindowManager.showErrorAlert("Login is busy. Please, try another one.");

        if (repMsg.equals(Commands.REGISTEROK))
            WindowManager.showInfoAlert("Account was successfully registered.");

        if (repMsg.equals(Commands.CHANGEPASSWRONG))
            WindowManager.showErrorAlert("Wrong login or password.");

        if (repMsg.equals(Commands.CHANGEPASSOK))
            WindowManager.showInfoAlert("Password was successfully changed.");

        if (repMsg.equals(Commands.REGISTERFAIL))
            WindowManager.showErrorAlert("Registration failed. Please, try again later.");

        if (repMsg.equals(Commands.DELETEFAIL))
            WindowManager.showErrorAlert("Failed to delete file at server.");
    }
}
