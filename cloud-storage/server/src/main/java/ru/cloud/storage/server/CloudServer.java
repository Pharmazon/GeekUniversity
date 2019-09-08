package ru.cloud.storage.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import ru.cloud.storage.server.handler.AuthGatewayHandler;
import ru.cloud.storage.server.handler.RegisterChangePasswordHandler;
import ru.cloud.storage.server.util.Assets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class CloudServer {
    private int propPort;
    private int propMaxObjSize;

    private void run() throws Exception {
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            this.readPropertiesFromFile();
            ServerBootstrap b = new ServerBootstrap();
            b.group(mainGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    new ObjectDecoder(propMaxObjSize,
                                            ClassResolvers.cacheDisabled(null)),
                                    new ObjectEncoder(),
                                    new RegisterChangePasswordHandler(),
                                    new AuthGatewayHandler()
                            );
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = b.bind(propPort).sync();
            future.channel().closeFuture().sync();
        } finally {
            mainGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private void readPropertiesFromFile() {
        try (Reader in = new InputStreamReader(this.getClass().getResourceAsStream("/server.properties"))) {
            Properties pr = new Properties();
            pr.load(in);

            this.propPort = Integer.parseInt(pr.getProperty("port"));
            this.propMaxObjSize = Integer.parseInt(pr.getProperty("max_obj_size"));
            int propBufferSize = Integer.parseInt(pr.getProperty("buffer_size"));
            Path propStorageServerPath = Paths.get(pr.getProperty("storage_folder"));

            Assets.getInstance().setServerPath(propStorageServerPath);
            Assets.getInstance().setBufferSize(propBufferSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new CloudServer().run();
    }
}
