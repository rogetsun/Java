package com.uv.audio.netty;

import com.uv.audio.dataChannel.impl.FrameChannel;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class BaseClient {

    static final String HOST = System.getProperty("host", "172.17.1.204");
    static final int PORT = Integer.parseInt(System.getProperty("port", "3332"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));
    private FrameChannel frameChannel;


    public static void main(String[] args) throws Exception {
        new BaseClient(new FrameChannel()).start();
    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new BaseClientHandler(frameChannel));
                        }
                    });

            ChannelFuture future = b.connect(HOST, PORT).sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public BaseClient(FrameChannel frameChannel) {
        this.frameChannel = frameChannel;
    }

    public FrameChannel getFrameChannel() {
        return frameChannel;
    }

    public void setFrameChannel(FrameChannel frameChannel) {
        this.frameChannel = frameChannel;
    }

}
