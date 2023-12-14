package dev.cobblesword.battleships;

import dev.cobblesword.battleships.game.BattleshipMatch;
import dev.cobblesword.battleships.network.handlers.WebSocketFrameHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
//        BattleshipMatch battleshipMatch = new BattleshipMatch();
//        battleshipMatch.setUpGame();
//        battleshipMatch.launchMissile(1, 1);
//        battleshipMatch.launchMissile(5, 5);
//        battleshipMatch.launchMissile(6, 5);
//        battleshipMatch.launchMissile(7, 5);
//        battleshipMatch.launchMissile(9, 5);
//        battleshipMatch.draw();


        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new WebSocketServerProtocolHandler("/websocket"));
                            pipeline.addLast(new WebSocketFrameHandler());
                        }
                    });

            Channel ch = b.bind(8080).sync().channel();
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}