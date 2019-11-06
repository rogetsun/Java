package com.uv.audio.player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.InputStream;
import java.io.PipedInputStream;

/**
 * @author uvsun 2019/11/6 11:57 上午
 */
public class Mp3PLayer implements Runnable{
    InputStream is;
    Player player;


    @Override
    public void run() {
        try {
            player = new Player(is);
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public Mp3PLayer(InputStream is) {
        this.is = is;
    }
}
