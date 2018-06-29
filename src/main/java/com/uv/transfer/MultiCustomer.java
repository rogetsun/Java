package com.uv.transfer;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * @author uvsun 2018/6/28 下午5:02
 */
public class MultiCustomer {
    private static TransferQueue<String> queue = new LinkedTransferQueue<>();

    private static Runnable producter = () -> {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 2; j++) {
                String good = "good" + i;
                queue.offer(good);
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {

            }

        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            Runnable customer = () -> {
                while (true) {
                    try {
                        System.out.println("customer" + finalI + " : " + queue.take());
                    } catch (InterruptedException e) {
                    }
                }
            };
            new Thread(customer).start();
        }
        new Thread(producter).start();
    }
}
