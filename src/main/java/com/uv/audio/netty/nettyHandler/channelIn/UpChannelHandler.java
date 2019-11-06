package com.uv.audio.netty.nettyHandler.channelIn;


import com.uv.audio.dataChannel.impl.FrameChannel;
import com.uv.audio.netty.CANDict;
import com.uv.audio.netty.Frame;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.InetSocketAddress;


/**
 * Created by clark on 2017/5/15.
 */
public class UpChannelHandler extends ChannelInboundHandlerAdapter {
    private static final Log log = LogFactory.getLog(UpChannelHandler.class);
    private FrameChannel frameUpChannel;

    public UpChannelHandler(FrameChannel frameUpChannel) {
        this.frameUpChannel = frameUpChannel;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Frame frame = (Frame) msg;
        InetSocketAddress inSocket = (InetSocketAddress) ctx.channel().remoteAddress();

        String clientIP = inSocket.getAddress().getHostAddress();
        try {
            frame.setClientIP(clientIP);
            this.frameUpChannel.put(frame);
        } catch (Exception e) {
            log.error("create sensor socket router error,", e);
        }
    }

}
