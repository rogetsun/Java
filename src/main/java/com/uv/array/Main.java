package com.uv.array;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author uvsun 2018/4/8 下午5:37
 */
public class Main {
    static Map<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int sleep = 20;

        ExecutorService es = Executors.newFixedThreadPool(7);
        es.execute(() -> {
            while (true) {
                map.put("1", "1");
                try {
                    TimeUnit.MILLISECONDS.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        es.execute(() -> {
            while (true) {

                map.put("2", "2");
                try {
                    TimeUnit.MILLISECONDS.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        es.execute(() -> {
            while (true) {
                map.remove("1");
                try {
                    TimeUnit.MILLISECONDS.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        es.execute(() -> {
            while (true) {
                map.remove("2");
                try {
                    TimeUnit.MILLISECONDS.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        es.execute(() -> {
            while (true) {
                map.remove("1");
                try {
                    TimeUnit.MILLISECONDS.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        es.execute(() -> {
            while (true) {
                map.remove("2");
                try {
                    TimeUnit.MILLISECONDS.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        es.execute(() -> {
            while (true) {
                try {
                    Object[] key = map.keySet().toArray();
                    for (Object k : key) {
                        System.out.println(k + ":" + map.get(k));
                    }
                } catch (NegativeArraySizeException e1) {
                    e1.printStackTrace();
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
