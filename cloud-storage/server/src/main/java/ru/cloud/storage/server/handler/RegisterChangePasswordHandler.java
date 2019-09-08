package ru.cloud.storage.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import ru.cloud.storage.common.Commands;
import ru.cloud.storage.common.message.CmdMsg;
import ru.cloud.storage.server.service.AuthService;
import ru.cloud.storage.server.util.Assets;
import ru.cloud.storage.server.util.ServerUtilities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RegisterChangePasswordHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg == null)
            return;
        AuthService authService = Assets.getInstance().getAuthService();

        if (msg instanceof CmdMsg) {
            CmdMsg cmdMsg = (CmdMsg) msg;

            if (cmdMsg.equals(Commands.REGISTER)) {
                String login = cmdMsg.getAttachment()[0].toString();
                String password = cmdMsg.getAttachment()[1].toString();
                String name = cmdMsg.getAttachment()[2].toString();

                if (!authService.isLoginBusy(login)) {
                    if (authService.userRegister(login, password, name)) {
                        Path ssp = Assets.getInstance().getServerPath();
                        Files.createDirectory(Paths.get(ssp.toString(), login));
                        ServerUtilities.sendReply(Commands.REGISTEROK, ctx);
                    } else {
                        ServerUtilities.sendReply(Commands.REGISTERFAIL, ctx);
                    }
                } else {
                    ServerUtilities.sendReply(Commands.LOGINBUSY, ctx);
                }
            } else if (cmdMsg.equals(Commands.CHANGEPASS)) {
                String login = cmdMsg.getAttachment()[0].toString();
                String oldPass = cmdMsg.getAttachment()[1].toString();
                String newPass = cmdMsg.getAttachment()[2].toString();

                if (authService.changePassword(login, oldPass, newPass)) {
                    ServerUtilities.sendReply(Commands.CHANGEPASSOK, ctx);
                } else {
                    ServerUtilities.sendReply(Commands.CHANGEPASSWRONG, ctx);
                }
            } else {
                ctx.fireChannelRead(msg);
                ctx.flush();
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