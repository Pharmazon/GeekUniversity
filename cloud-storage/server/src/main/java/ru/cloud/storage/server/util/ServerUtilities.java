package ru.cloud.storage.server.util;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import ru.cloud.storage.common.Commands;
import ru.cloud.storage.common.message.FileListMsg;
import ru.cloud.storage.common.message.FileMsg;
import ru.cloud.storage.common.message.ReplyMsg;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ServerUtilities {

    public static void sendReply(Commands command, ChannelHandlerContext ctx) {
        ReplyMsg rm = new ReplyMsg(command);
        ChannelFuture future = ctx.writeAndFlush(rm);
        try {
            future.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sendFileList(Path path, ChannelHandlerContext ctx) {
        List<String> files = null;
        try {
            files = Files.list(path).map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileListMsg flMsg = new FileListMsg(files, path.toString());
        ChannelFuture future = ctx.writeAndFlush(flMsg);
        try {
            future.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sendObject(Object object, ChannelHandlerContext ctx) {
        ctx.writeAndFlush(object);
    }

    public static void sendFile(FileMsg fileMsg, int bufferSize, ChannelHandlerContext ctx) {
        fileMsg.setBufferSize(bufferSize);
        sendObject(fileMsg, ctx);

        InputStream fis = null;
        byte[] byteArray = new byte[bufferSize];
        long fileSize = fileMsg.getSize();

        try {
            fis = Files.newInputStream(Paths.get(fileMsg.getSource()));
            while (fileSize > 0) {
                int i = fis.read(byteArray);
                sendObject(byteArray, ctx);
                fileSize -= i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
