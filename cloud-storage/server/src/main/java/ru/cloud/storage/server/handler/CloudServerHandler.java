package ru.cloud.storage.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import ru.cloud.storage.common.Commands;
import ru.cloud.storage.common.message.CmdMsg;
import ru.cloud.storage.common.message.FileMsg;
import ru.cloud.storage.server.util.Assets;
import ru.cloud.storage.server.util.ServerUtilities;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.util.Date;

public class CloudServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client connected...");
        ctx.write("Welcome to " + InetAddress.getLocalHost().getHostAddress() + "!\r\n");
        ctx.write("It is " + new Date() + " now.\r\n");
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg == null)
                return;

            Path serverPath = Assets.getInstance().getServerPath();
            boolean isRequestFileList = false;

            if (msg instanceof CmdMsg) {
                CmdMsg cmdMsg = (CmdMsg) msg;

                if (cmdMsg.equals(Commands.GETFILELIST)) {
                    Path target = Paths.get((String) cmdMsg.getAttachment()[0]);
                    ServerUtilities.sendFileList(target, ctx);
                }

                if (cmdMsg.equals(Commands.CREATEDIR)) {
                    Path target = Paths.get((String) cmdMsg.getAttachment()[0]);
                    if (cmdMsg.getAttachment().length == 1)
                        isRequestFileList = true;
                    if (cmdMsg.getAttachment().length == 2)
                        isRequestFileList = (boolean) cmdMsg.getAttachment()[1];
                    Files.createDirectory(target);
                    if (isRequestFileList)
                        ServerUtilities.sendFileList(target, ctx);
                }

                if (cmdMsg.equals(Commands.RENAME)) {
                    Path path = Paths.get((String) cmdMsg.getAttachment()[0]);
                    String newName = (String) cmdMsg.getAttachment()[1];
                    Files.move(path, path.resolveSibling(newName), StandardCopyOption.REPLACE_EXISTING);
                    Path subPath = path.subpath(1, path.getNameCount() - 1);
                    path = Paths.get(serverPath.toString(), subPath.toString());
                    ServerUtilities.sendFileList(path, ctx);
                }

                if (cmdMsg.equals(Commands.REFRESH)) {
                    Path dir = Paths.get((String) cmdMsg.getAttachment()[0]);
                    ServerUtilities.sendFileList(dir, ctx);
                }

                if (cmdMsg.equals(Commands.LOGOUT)) {
                    boolean isNeedReply = (boolean) cmdMsg.getAttachment()[0];
                    if (isNeedReply) {
                        AuthGatewayHandler.setAuthorized(false);
                        ctx.pipeline().removeLast();
                        ctx.pipeline().removeLast();
                        ServerUtilities.sendReply(Commands.LOGOUTOK, ctx);
                    } else {
                        AuthGatewayHandler.setAuthorized(false);
                        ctx.pipeline().removeLast();
                        ctx.pipeline().removeLast();
                    }
                }

                if (cmdMsg.equals(Commands.OPENDIR)) {
                    Path dir = Paths.get((String) cmdMsg.getAttachment()[0]);
                    ServerUtilities.sendFileList(dir, ctx);
                }

                if (cmdMsg.equals(Commands.DELETEFILEDIR)) {
                    Path path = Paths.get((String) cmdMsg.getAttachment()[0]);

                    Files.walkFileTree(path, new FileVisitor<Path>() {
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
                        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
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

                    Path subPath = path.subpath(1, path.getNameCount() - 1);
                    path = Paths.get(serverPath.toString(), subPath.toString());
                    ServerUtilities.sendFileList(path, ctx);
                }
            }

            if (msg instanceof FileMsg) {
                ctx.fireChannelRead(msg);
            }

            if (msg instanceof byte[]) {
                ctx.fireChannelRead(msg);
            }

            if (msg instanceof CmdMsg && ((CmdMsg) msg).equals(Commands.DOWNLOADFILEFOLDER)) {
                ctx.fireChannelRead(msg);
            }
        } finally {
            ReferenceCountUtil.release(msg);
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
