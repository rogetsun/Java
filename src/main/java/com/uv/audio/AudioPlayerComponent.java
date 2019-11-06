package com.uv.audio;

import com.uv.audio.dataChannel.DataChannel;
import com.uv.audio.dataChannel.impl.FrameChannel;
import com.uv.audio.netty.Frame;
import com.uv.audio.player.Mp3PLayer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author uvsun 2019/11/6 1:58 下午
 */
public class AudioPlayerComponent {
    private static final Log log = LogFactory.getLog(AudioPlayerComponent.class);

    private DataChannel<Frame> frameDataChannel = new FrameChannel();

    private boolean stopFlag;

    private ExecutorService mp3PlayerExecutor = Executors.newSingleThreadExecutor(new ThreadFactory("Mp3Player"));
    private ExecutorService monitorExecutor = Executors.newSingleThreadExecutor(new ThreadFactory("Monitor"));
    private ExecutorService componentExecutor = Executors.newSingleThreadExecutor(new ThreadFactory("ComponentExecutor"));

    public void start() {
        PipedOutputStream pipedOutputStream = this.startMp3Player();

        this.startReceive(pipedOutputStream);

        startMonitor();
    }

    private void startReceive(PipedOutputStream pipedOutputStream) {
        componentExecutor.execute(() -> {
            if (null != pipedOutputStream) {
                log.debug("sleep 2");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {

                }

                while (!stopFlag) {
                    try {
                        Frame f = frameDataChannel.get();
                        byte[] bytes = f.getData();
                        pipedOutputStream.write(bytes);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                mp3PlayerExecutor.shutdown();
            }
        });
    }

    private PipedOutputStream startMp3Player() {
        PipedInputStream is = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        try {
            pipedOutputStream.connect(is);
            Mp3PLayer mp3PLayer = new Mp3PLayer(is);
            mp3PlayerExecutor.execute(mp3PLayer);
            return pipedOutputStream;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void startMonitor() {
        frameDataChannel.setCalculateFlag(true);
        frameDataChannel.setSpeedCycle(1);
        monitorExecutor.execute(() -> {
            while (!stopFlag) {
                try {
                    log.debug(frameDataChannel.size() + ":\t" + frameDataChannel.getInSpeedPerSecond() * 8 + " in,\t" + frameDataChannel.getOutSpeedPerSecond() * 8 + " out");
                    TimeUnit.SECONDS.sleep(1);
                } catch (Throwable e) {
                    e.printStackTrace();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {

                    }

                }
            }
        });
    }

    public DataChannel<Frame> getFrameDataChannel() {
        return frameDataChannel;
    }

    public void setFrameDataChannel(DataChannel<Frame> frameDataChannel) {
        this.frameDataChannel = frameDataChannel;
    }

    public boolean isStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }
}
