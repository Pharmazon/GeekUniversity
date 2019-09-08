package ru.cloud.storage.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import ru.cloud.storage.common.Commands;
import ru.cloud.storage.common.message.CmdMsg;
import ru.cloud.storage.common.message.FileMsg;
import ru.cloud.storage.common.util.MD5Checksum;
import ru.cloud.storage.server.util.Assets;
import ru.cloud.storage.server.util.ServerUtilities;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FilesHandler extends ChannelInboundHandlerAdapter {
    private Path dstFolder;
    private Path dstFile;
    private long fileSize;
    private int bufferSize;
    private boolean flag;
    private OutputStream os;
    private String crc;
    private boolean isRequestFileList;
    private Queue<FileMsg> downloadQueue = new LinkedList<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg == null)
            return;

        if (msg instanceof CmdMsg) {
            CmdMsg cmdMsg = (CmdMsg) msg;

            if (cmdMsg.equals(Commands.DOWNLOADFILEFOLDER)) {
                Path src = Paths.get((String) cmdMsg.getAttachment()[0]);
                Path dst = Paths.get((String) cmdMsg.getAttachment()[1]);
                boolean isDirectory = (boolean) cmdMsg.getAttachment()[2];

                if (!isDirectory) {
                    FileMsg fileMsg = new FileMsg(src, dst, false);
                    this.bufferSize = Assets.getInstance().getBufferSize();
                    ServerUtilities.sendFile(fileMsg, this.bufferSize, ctx);
                }

                if (isDirectory) {
                    Files.walkFileTree(src, new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                            Path path = src.getParent();
                            Path rel = path.relativize(dir);
                            Path target = Paths.get(dst.toString(), rel.toString());
                            CmdMsg repCmd = new CmdMsg(Commands.CREATEDIR, target.toString());
                            ServerUtilities.sendObject(repCmd, ctx);
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            Path subFile = src.getParent().relativize(file);
                            Path target = Paths.get(dst.toString(), subFile.toString()).getParent();
                            FileMsg fileMsg = new FileMsg(file, target, false);
                            downloadQueue.offer(fileMsg);
                            return FileVisitResult.CONTINUE;
                        }
                    });

                    while (!downloadQueue.isEmpty()) {
                        FileMsg fileMsg = downloadQueue.poll();
                        this.bufferSize = Assets.getInstance().getBufferSize();
                        ServerUtilities.sendFile(fileMsg, this.bufferSize, ctx);
                    }
                }
            }
        }

        if (msg instanceof FileMsg && !flag) {
            FileMsg fileMsg = (FileMsg) msg;
            String filename = fileMsg.getFilename();
            this.dstFolder = Paths.get(fileMsg.getDestination());
            this.dstFile = Paths.get(dstFolder.toString(), filename);
            this.fileSize = fileMsg.getSize();
            this.crc = fileMsg.getCrc();
            this.bufferSize = fileMsg.getBufferSize();
            this.flag = true;
            this.isRequestFileList = fileMsg.isRequestFileList();
            if (Files.exists(dstFile)) {
                Files.deleteIfExists(dstFile);
            }
            Files.createFile(dstFile);
            this.os = Files.newOutputStream(dstFile, StandardOpenOption.APPEND);
        }

        if (flag && msg instanceof byte[]) {
            byte[] byteArray = (byte[]) msg;
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
                if (crc.equals(newCrc)) {
                    if (isRequestFileList) {
                        ServerUtilities.sendFileList(dstFolder, ctx);
                    }
                } else {
                    Files.deleteIfExists(dstFile);
                    ServerUtilities.sendReply(Commands.UPLOADFAIL, ctx);
                }
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.flush();
        ctx.close();
    }
}
