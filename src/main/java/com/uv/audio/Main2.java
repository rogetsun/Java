package com.uv.audio;

import com.uv.audio.dataChannel.impl.FrameChannel;
import com.uv.audio.netty.Frame;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author uvsun 2019/11/6 2:44 下午
 */
public class Main2 {
    private static final Log log = LogFactory.getLog(Main2.class);

    public static void main(String[] args) throws IOException {
        FrameChannel frameChannel = new FrameChannel();

        AudioPlayerComponent com = new AudioPlayerComponent();
        com.setFrameDataChannel(frameChannel);
        com.start();

        File file = new File("/Users/uvsun/Music/hehe.mp3");
        FileInputStream fis = new FileInputStream(file);

        int nByteRead = 0;
        byte[] abData = new byte[8];
        //每秒写入多少字节
        int count = 4000;
        int c = 0;
        long t = System.currentTimeMillis();
        try {
            while (nByteRead != -1) {
                nByteRead = fis.read(abData, 0, abData.length);
                if (nByteRead >= 0) {
                    Frame f = new Frame();
                    f.setData(Arrays.copyOf(abData, 8));
                    frameChannel.put(f);
                    c += 8;
                    if (c >= count) {
                        long d = System.currentTimeMillis() - t;
                        if (d < 1000) {
                            d = 1000 - d;
                            log.debug("write:sleep" + d);
                            TimeUnit.MILLISECONDS.sleep(d);
                            c = 0;
                            t = System.currentTimeMillis();
                        }
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            fis.close();
        }

    }
}
