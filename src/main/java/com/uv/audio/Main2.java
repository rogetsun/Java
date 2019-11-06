package com.uv.audio;

import com.uv.audio.dataChannel.impl.FrameChannel;
import com.uv.audio.netty.Frame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author uvsun 2019/11/6 2:44 下午
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        FrameChannel frameChannel = new FrameChannel();

        AudioPlayerComponent com = new AudioPlayerComponent();
        com.setFrameDataChannel(frameChannel);
        com.start();

        File file = new File("/Users/uvsun/Music/毛泽少-告白气球的副本.mp3");
        FileInputStream fis = new FileInputStream(file);

        int nByteRead = 0;
        byte[] abData = new byte[8];
        try {
            while (nByteRead != -1) {
                nByteRead = fis.read(abData, 0, abData.length);
                if (nByteRead >= 0) {
                    Frame f = new Frame();
                    f.setData(Arrays.copyOf(abData, 8));
                    frameChannel.put(f);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            fis.close();
        }

    }
}
