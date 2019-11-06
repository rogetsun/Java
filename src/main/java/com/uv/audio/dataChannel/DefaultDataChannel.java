package com.uv.audio.dataChannel;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * Created by litx on 2017/5/13.
 */
public abstract class DefaultDataChannel<E> implements DataChannel<E> {

    private TransferQueue<E> queue = new LinkedTransferQueue<>();
    private long inCount = 0;
    private long outCount = 0;
    private long inCalculateTime = System.currentTimeMillis();
    private long outCalculateTime = System.currentTimeMillis();
    private boolean calculateFlag = false;

    private final List<E> emptyList = new ArrayList<>();

    private int speedCycle;
    private Float speed;

    private List<DataChannelDispatcher<E, ? extends E>> channelDispatchers;

    public DefaultDataChannel() {
    }

    @Override
    public void put(E cmd) throws InterruptedException {

        if (calculateFlag) {
            this.inCount++;
        }

        this.queue.put(cmd);

        if (null != this.channelDispatchers && this.channelDispatchers.size() > 0) {
            this.channelDispatchers.forEach(channelFilter -> channelFilter.dispatch(cmd));
        }
    }

    @Override
    public E get() throws InterruptedException {
        if (calculateFlag) {
            this.outCount++;
        }
        return this.queue.take();
    }

    @Override
    public E get(int seconds) throws InterruptedException {
        E cmd = this.queue.poll(seconds, TimeUnit.SECONDS);
        if (calculateFlag && cmd != null) {
            this.outCount++;
        }
        return cmd;
    }


    @Override
    public E poll() throws Exception {
        E e = this.queue.poll();
        if (null != e && calculateFlag) {
            this.outCount++;
        }
        return e;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] getToArray(int num, Class<? super E> clazz) {
        List<E> l = getToList(num);
        E[] array = (E[]) Array.newInstance(clazz, l.size());
        array = l.toArray(array);
        return array;
    }

    @Override
    public List<E> getToList(int num) {
        if (this.size() > 0) {
            List<E> list = new ArrayList<>();
            this.queue.drainTo(list, num);
            if (calculateFlag) {
                this.outCount += list.size();
            }
            return list;
        } else {
            return this.emptyList;
        }
    }

    @Override
    public List<E> getAll() {
        if (this.size() > 0) {
            List<E> list = new ArrayList<>();
            this.queue.drainTo(list);
            if (calculateFlag) {
                this.outCount += list.size();
            }
            return list;
        } else {
            return this.emptyList;
        }
    }

    @Override
    public int size() {
        return this.queue.size();
    }

    @Override
    public Float getInSpeedPerSecond() {
        if (System.currentTimeMillis() - (speedCycle * 1000) > inCalculateTime) {
            long now = System.currentTimeMillis();
            this.speed = Float.valueOf(DECIMAL_FORMAT.format(inCount / ((now - inCalculateTime) / 1000.0)));
            inCount = 0;
            inCalculateTime = now;
        }
        return speed;
    }

    @Override
    public Float getOutSpeedPerSecond() {
        if (System.currentTimeMillis() - (speedCycle * 1000) > outCalculateTime) {
            long now = System.currentTimeMillis();
            this.speed = Float.valueOf(DECIMAL_FORMAT.format(outCount / ((now - outCalculateTime) / 1000.0)));
            outCount = 0;
            outCalculateTime = now;
        }
        return speed;
    }

    @Override
    public List<DataChannelDispatcher<E, ? extends E>> getChannelDispatchers() {
        return this.channelDispatchers;
    }

    @Override
    public void setChannelDispatchers(List<DataChannelDispatcher<E, ? extends E>> dataChannelDispatchers) {
        this.channelDispatchers = dataChannelDispatchers;
    }

    @Override
    public long getAllThroughCount() {
        return outCount;
    }

    @Override
    public void startCalculate() {
        this.calculateFlag = true;
    }

    @Override
    public void stopCalculate() {
        this.calculateFlag = false;
    }

    @Override
    public void setCalculateFlag(boolean calculateFlag) {
        this.calculateFlag = calculateFlag;
    }

    @Override
    public boolean isCalculateFlag() {
        return this.calculateFlag;
    }

    @Override
    public void setSpeedCycle(int cycle) {
        this.speedCycle = cycle;
    }

    @Override
    public int getSpeedCycle() {
        return this.speedCycle;
    }


}
