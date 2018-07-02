package com.uv.transfer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author uvsun 2018/7/2 下午1:48
 */
public class TestPriorityQueue {
    public static void main(String[] args) {

        Comparator<Item> comparator = (o1, o2) -> o2.getPriority() - o1.getPriority();

        PriorityBlockingQueue<Item> queue = new PriorityBlockingQueue<>(10, comparator);

        List<Item> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Item item = new Item(10 - i);
            queue.add(item);
            list.add(item);
        }

        list.sort(comparator);

        System.out.println(list);

        Runnable r = () -> {
            while (true) {
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(r).start();
    }

    static class Item {
        private int priority;

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public Item(int priority) {
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "priority=" + priority +
                    '}';
        }
    }
}
