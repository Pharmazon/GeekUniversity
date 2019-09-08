package ru.cloud.storage.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import ru.cloud.storage.client.entity.StoragePath;
import ru.cloud.storage.client.entity.TableEntry;
import ru.cloud.storage.client.util.Assets;
import ru.cloud.storage.client.util.Network;
import ru.cloud.storage.client.util.WindowManager;
import ru.cloud.storage.common.Commands;
import ru.cloud.storage.common.message.CmdMsg;
import ru.cloud.storage.common.message.FileMsg;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.util.List;
import java.util.Queue;
import java.util.*;
import java.util.stream.Collectors;

public class MainController {
    private ObservableList<TableEntry> listLocal = FXCollections.observableArrayList();
    private ObservableList<TableEntry> listServer = FXCollections.observableArrayList();
    private StoragePath spLocal = Assets.getInstance().getLocalPath();
    private StoragePath spServer = Assets.getInstance().getServerPath();
    private int bufferSize;
    private Queue<FileMsg> uploadQueue;

    @FXML
    private Text pathLocalText;

    @FXML
    private Text pathServerText;

    @FXML
    private TableView<TableEntry> localTable;

    @FXML
    private TableView<TableEntry> serverTable;

    @FXML
    private TableColumn<TableEntry, Integer> idColLocal;

    @FXML
    private TableColumn<TableEntry, String> nameColLocal;

    @FXML
    private TableColumn<TableEntry, String> typeColLocal;

    @FXML
    private TableColumn<TableEntry, String> sizeColLocal;

    @FXML
    private TableColumn<TableEntry, Integer> idColServer;

    @FXML
    private TableColumn<TableEntry, String> nameColServer;

    @FXML
    private TableColumn<TableEntry, String> typeColServer;

    @FXML
    private TableColumn<TableEntry, String> sizeColServer;

    @FXML
    private Button btnUpload;

    @FXML
    private Button btnDownload;

    @FXML
    private void initialize() {
        this.bufferSize = Assets.getInstance().getBufferSize();
        this.uploadQueue = new LinkedList<>();
        Network.getInstance().setMainController(this);
        refillLocalTable();
        refillServerTable();

//        this.initLocalDirectoryWatcher();
        this.initGUI();
    }

    private void outsideDirLocal() {
        spLocal.outside();
        refillLocalTable();
        this.updateText(spLocal, pathLocalText, true);
    }

    private void insideDirLocal(Path path) {
        spLocal.inside(path);
        refillLocalTable();
        this.updateText(spLocal, pathLocalText, true);
    }

    public void refillLocalTable() {
        listLocal.clear();
        List<Path> files = null;

        try {
            files = Files.list(spLocal.getFullPath()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int rowsCount = files != null ? files.size() : 0;
        if (!spLocal.isRoot()) {
            listLocal.add(new TableEntry());
        }
        for (Path file : files) {
            rowsCount += 1;
            listLocal.add(new TableEntry(rowsCount, file));
        }

        this.sortList(listLocal, spLocal);
        this.updateText(spLocal, pathLocalText, true);
    }

    public void refillServerTable() {
        listServer.clear();
        List<Path> filesFromServer = Network.getInstance().getFileListServer();

        int rowsCount = filesFromServer.size();
        if (!spServer.isRoot()) {
            listServer.add(new TableEntry());
        }
        for (Path file : filesFromServer) {
            rowsCount += 1;
            listServer.add(new TableEntry(rowsCount, file));
        }
        this.sortList(listServer, spServer);
        this.updateText(spServer, pathServerText, false);
    }

    private void updateText(StoragePath sp, Text text, boolean isAbsPath) {
        if (isAbsPath) {
            text.setText(sp.toAbsString());
        } else {
            text.setText(sp.toRelString());
        }
    }

    private void sortList(ObservableList<TableEntry> list, StoragePath sp) {
        Comparator<TableEntry> comp = (o1, o2) -> {
            if (o1.isDirectory() && o2.isFile()) {
                return -1;
            }
            if (o1.isFile() && o2.isDirectory()) {
                return 1;
            }
            if (o1.isFile() && o2.isFile()) {
                return o1.getShortFilename().compareTo(o2.getShortFilename());
            }
            if (o1.isDirectory() && o2.isDirectory()) {
                return o1.getShortFilename().compareTo(o2.getShortFilename());
            }
            return 0;
        };
        list.sort(comp);

        for (int i = 0; i < list.size(); i++) {
            TableEntry entry = list.get(i);
            if (!sp.isRoot() && entry.isMove())
                entry.setId(null);
            else if (sp.isRoot() && !entry.isMove())
                entry.setId(i + 1);
            else entry.setId(i);
        }
    }

    public void renameLocal() {
        TableEntry selectedEntry = localTable.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            Path file = selectedEntry.getFullPath();
            try {
                Optional<String> btn = WindowManager.showInputRename(file);
                if (btn.isPresent()) {
                    String newName = btn.get();
                    Files.move(file, file.resolveSibling(newName), StandardCopyOption.REPLACE_EXISTING);
                    refillLocalTable();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadToServer() {
        TableEntry selectedEntry = localTable.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            btnUpload.setDisable(true);
            Path src = selectedEntry.getFullPath();
            Path dst = spServer.getFullPath();
            if (!Files.exists(src)) return;

            if (Files.isRegularFile(src))
                this.uploadFile(src, dst);
            if (Files.isDirectory(src))
                this.uploadDirectory(src, dst);
        }
    }

    private void uploadFile(Path src, Path dst) {
        Thread uploadFileThread = new Thread(() -> {
            Network.getInstance().sendFile(src, dst, bufferSize);
        });
        uploadFileThread.start();
        btnUpload.setDisable(false);
    }

    private void uploadDirectory(Path src, Path dst) {
        Thread uploadDirThread = new Thread(() -> {
            try {
                Files.walkFileTree(src, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        Path subDir = spLocal.getFullPath().relativize(dir);
                        Path dst = Paths.get(spServer.getFullPath().toString(), subDir.toString());
                        createDirServer(dst);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Path subFile = spLocal.getFullPath().relativize(file);
                        Path relFolder = subFile.subpath(0, subFile.getNameCount() - 1);
                        Path target = Paths.get(dst.toString(), relFolder.toString());
                        FileMsg fileMsg = new FileMsg(file, target, false);
                        uploadQueue.offer(fileMsg);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                while (!uploadQueue.isEmpty()) {
                    FileMsg fileMsg = uploadQueue.poll();
                    Network.getInstance().sendFile(fileMsg, bufferSize);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            CmdMsg cmdMsg = new CmdMsg(Commands.GETFILELIST, spServer.toAbsString());
            Network.getInstance().sendObject(cmdMsg);
        });
        uploadDirThread.start();
        btnUpload.setDisable(false);
    }

    public void renameAtServer() {
        TableEntry selectedEntry = serverTable.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            Path file = selectedEntry.getFullPath();
            Optional<String> btn = WindowManager.showInputRename(file);
            if (btn.isPresent()) {
                String newName = btn.get();
                CmdMsg cmdMsg = new CmdMsg(Commands.RENAME, file.toString(), newName);
                Network.getInstance().sendObject(cmdMsg);
            }
        }
    }

    public void deleteAtServer() {
        TableEntry selectedEntry = serverTable.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            Optional<ButtonType> btn = WindowManager.showDeleteConfirmation(selectedEntry.getFullPath());
            if (btn.isPresent() && btn.get() == ButtonType.OK) {
                Path path = selectedEntry.getFullPath();
                CmdMsg cmdMsg = new CmdMsg(Commands.DELETEFILEDIR, path.toString());
                Network.getInstance().sendObject(cmdMsg);
            }
        }
    }

    public void downloadFromServer() {
        TableEntry selectedEntry = serverTable.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            btnDownload.setDisable(true);
            Path srcPath = selectedEntry.getFullPath();
            Path dstPath = spLocal.getFullPath();

            boolean isDirectory = false;
            if (!Files.exists(srcPath)) return;

            if (Files.isDirectory(srcPath))
                isDirectory = true;
            if (Files.isRegularFile(srcPath))
                isDirectory = false;

            CmdMsg cmdMsg = new CmdMsg(Commands.DOWNLOADFILEFOLDER, srcPath.toString(),
                    dstPath.toString(), isDirectory);
            Network.getInstance().sendObject(cmdMsg);
            btnDownload.setDisable(false);
        }
    }

    public void addLocal() {
        List<Path> files = WindowManager.addFilesDialog();
        if (files != null) {
            for (Path src : files) {
                if (Files.exists(src)) {
                    Path dst = Paths.get(spLocal.getFullPath().toString(), src.getFileName().toString());
                    try {
                        Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
                        refillLocalTable();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void openDir() {
        try {
//            Runtime.getRuntime().exec("explorer.exe " + Paths.get(spLocal.getFullPath().toString()));
            try {
                Desktop.getDesktop().browse(new URI(spLocal.getFullPath().toString()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDirLocal() {
        Path path = spLocal.getFullPath();
        Optional<String> btn = WindowManager.showInputCreateDir(path);
        if (btn.isPresent()) {
            try {
                String newDirName = btn.get();
                Files.createDirectory(Paths.get(path.toString(), newDirName));
                refillLocalTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteLocalFile(TableEntry entry) {
        try {
            Optional<ButtonType> btn = WindowManager.showDeleteConfirmation(entry.getFullPath());
            if (btn.isPresent() && btn.get() == ButtonType.OK) {
                Path file = entry.getFullPath();
                DosFileAttributeView attr = Files.getFileAttributeView(file, DosFileAttributeView.class);
                attr.setReadOnly(false);
                Files.deleteIfExists(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteLocalDir(TableEntry entry) {
        try {
            Files.walkFileTree(entry.getFullPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    DosFileAttributeView attr = Files.getFileAttributeView(dir, DosFileAttributeView.class);
                    attr.setReadOnly(false);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    DosFileAttributeView attr = Files.getFileAttributeView(file, DosFileAttributeView.class);
                    attr.setReadOnly(false);
                    Files.deleteIfExists(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    DosFileAttributeView attr = Files.getFileAttributeView(dir, DosFileAttributeView.class);
                    attr.setReadOnly(false);
                    Files.deleteIfExists(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteLocal() {
        TableEntry selectedEntry = localTable.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            if (selectedEntry.isDirectory()) {
                deleteLocalDir(selectedEntry);
            } else {
                deleteLocalFile(selectedEntry);
            }
            refillLocalTable();
        }
    }

    public void logOut() {
        Optional<ButtonType> btn = WindowManager.showLogOutConfirmation();
        if (btn.isPresent()) {
            if (btn.get() == ButtonType.OK) {
                CmdMsg msg = new CmdMsg(Commands.LOGOUT, true);
                Network.getInstance().sendObject(msg);
            }
        }
    }

    public void refreshLocal() {
        this.refillLocalTable();
    }

    public void refreshServer() {
        CmdMsg cmdMsg = new CmdMsg(Commands.REFRESH, spServer.toString());
        Network.getInstance().sendObject(cmdMsg);
    }

    public void createDirServer() {
        Optional<String> btn = WindowManager.showInputCreateDir(spServer.getFullPath());
        if (btn.isPresent()) {
            String newDirName = btn.get();
            Path target = Paths.get(spServer.getFullPath().toString(), newDirName);
            CmdMsg cmdMsg = new CmdMsg(Commands.CREATEDIR, target.toString());
            Network.getInstance().sendObject(cmdMsg);
        }
    }

    private void createDirServer(Path target) {
        CmdMsg cmdMsg = new CmdMsg(Commands.CREATEDIR, target.toString(), false);
        Network.getInstance().sendObject(cmdMsg);
    }

    private void initLocalDirectoryWatcher() {
        Thread watchThread = new Thread(() -> {
            try {
                WatchService watcher = FileSystems.getDefault().newWatchService();
                Path dir = Assets.getInstance().getHome();
                WatchKey key = dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
                while (true) {
                    for (WatchEvent<?> event : key.pollEvents()) {
                        this.refillLocalTable();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        watchThread.setDaemon(true);
        watchThread.start();
    }

    private void initGUI() {
        idColLocal.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColLocal.setCellValueFactory(new PropertyValueFactory<>("shortFilename"));
//        nameColLocal.setCellFactory(param -> new MyCell());
        typeColLocal.setCellValueFactory(new PropertyValueFactory<>("type"));
        sizeColLocal.setCellValueFactory(new PropertyValueFactory<>("size"));
        idColLocal.setStyle("-fx-alignment: CENTER;");
        typeColLocal.setStyle("-fx-alignment: CENTER;");
        localTable.setItems(listLocal);

        idColServer.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColServer.setCellValueFactory(new PropertyValueFactory<>("shortFilename"));
        typeColServer.setCellValueFactory(new PropertyValueFactory<>("type"));
        sizeColServer.setCellValueFactory(new PropertyValueFactory<>("size"));
        idColServer.setStyle("-fx-alignment: CENTER;");
        typeColServer.setStyle("-fx-alignment: CENTER;");
        serverTable.setItems(listServer);

        localTable.setRowFactory(tr -> {
            TableRow<TableEntry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    TableEntry entry = row.getItem();
                    if (entry.isDirectory()) {
                        this.insideDirLocal(entry.getFullPath());
                    } else if (entry.isMove()) {
                        this.outsideDirLocal();
                    } else {
                        try {
                            Desktop.getDesktop().open(new File(entry.getFullPath().toString()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            return row;
        });

        serverTable.setRowFactory(tr -> {
            TableRow<TableEntry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    TableEntry entry = row.getItem();
                    if (entry.isDirectory()) {
                        String folderToOpen = entry.getShortFilename();
                        Path path = Paths.get(spServer.getFullPath().toString(), folderToOpen);
                        CmdMsg cmdMsg = new CmdMsg(Commands.OPENDIR, path.toString());
                        Network.getInstance().sendObject(cmdMsg);
                    } else if (entry.isMove()) {
                        Path path = spServer.outside();
                        CmdMsg cmdMsg = new CmdMsg(Commands.OPENDIR, path.toString());
                        Network.getInstance().sendObject(cmdMsg);
                    }
                }
            });
            return row;
        });
    }
}
