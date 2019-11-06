package com.uv.audio;

import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author uvsun 2019/11/6 12:18 下午
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        pipedInputStream.connect(pipedOutputStream);
        File f = new File("/Users/uvsun/Music/1.mp3");
        FileInputStream fis = new FileInputStream(f);

        Mp3PLayer pLayer = new Mp3PLayer(pipedInputStream);
        executorService.execute(pLayer);
        int nByteRead = 0;
        byte[] abData = new byte[512];
        try {
            while (nByteRead != -1) {
                nByteRead = fis.read(abData, 0, abData.length);
                if (nByteRead >= 0) {
                    pipedOutputStream.write(abData, 0, nByteRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
            pipedInputStream.close();
            pipedOutputStream.close();
        }
    }
}
