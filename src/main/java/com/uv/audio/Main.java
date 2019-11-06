package com.uv.audio;

import com.uv.audio.dataChannel.impl.FrameChannel;
import com.uv.audio.netty.BaseClient;

/**
 * @author uvsun 2019/11/6 2:30 下午
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        FrameChannel frameChannel = new FrameChannel();
        BaseClient baseClient = new BaseClient(frameChannel);
        AudioPlayerComponent component = new AudioPlayerComponent();
        baseClient.setFrameChannel(frameChannel);
        component.setFrameDataChannel(frameChannel);
        component.start();
        baseClient.start();
    }
}
