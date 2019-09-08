package ru.cloud.storage.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import ru.cloud.storage.common.Commands;
import ru.cloud.storage.common.message.AuthMsg;
import ru.cloud.storage.server.service.AuthService;
import ru.cloud.storage.server.util.Assets;
import ru.cloud.storage.server.util.ServerUtilities;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AuthGatewayHandler extends ChannelInboundHandlerAdapter {
    private static boolean authorized = false;

    public static void setAuthorized(boolean authorized) {
        AuthGatewayHandler.authorized = authorized;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg == null) {
            return;
        }
        if (!authorized) {
            if (msg instanceof AuthMsg) {
                AuthMsg authMsg = (AuthMsg) msg;
                AuthService authService = Assets.getInstance().getAuthService();
                String login = authMsg.getLogin();
                String password = authMsg.getPassword();
                String username = authService.authOK(authMsg.getLogin(), password);
                if (username != null) {
                    authorized = true;
                    Path path = Paths.get(Assets.getInstance().getServerPath().toString(), login);
                    ServerUtilities.sendFileList(path, ctx);
                    ServerUtilities.sendReply(Commands.AUTHOK, ctx);
                    ctx.pipeline().addLast(new CloudServerHandler());
                    ctx.pipeline().addLast(new FilesHandler());
                } else {
                    ServerUtilities.sendReply(Commands.AUTHWRONG, ctx);
                }
            } else {
                ReferenceCountUtil.release(msg);
            }
        } else {
            ctx.fireChannelRead(msg);
            ctx.flush();
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
