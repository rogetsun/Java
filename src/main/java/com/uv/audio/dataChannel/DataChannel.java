package com.uv.audio.dataChannel;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by uv2sun on 2017/5/10.
 * 数据通道
 * 先入先出管道，无数据时阻塞。数据慢时阻塞。
 */
public interface DataChannel<E> {
    //通道最大数据量
    int DATA_CHANNEL_SIZE = 10000;
    //放入满时，被阻塞重复放入的时间间隔,unit:millisecond
    int DATA_CHANNEL_FULL_PUT_DURATION = 20;

    DecimalFormat DECIMAL_FORMAT = new DecimalFormat(".00");

    /**
     * 添加数据
     *
     * @param e
     */
    void put(E e) throws Exception;

    /**
     * 提取数据无限等待
     *
     * @return
     */
    E get() throws Exception;

    /**
     * 提取数据，等待seconds秒
     *
     * @param seconds
     * @return
     */
    E get(int seconds) throws Exception;

    /**
     * 提取数据如果没有数据返回null
     *
     * @return
     */
    E poll() throws Exception;


    /**
     * 从Channel中获取指定个数个元素，如果通道内个数小于num个，则以通道最大个数为准。
     *
     * @param num 个数
     */
    E[] getToArray(int num, Class<? super E> clazz);

    /**
     * 从channel中获取指定个数个元素到List，如果通道内个数小于num个，则以通道最大个数为准。
     *
     * @param num 要元素个数
     */
    List<E> getToList(int num);

    /**
     * @return
     */
    List<E> getAll();

    /**
     * 通道内数据个数
     *
     * @return
     */
    int size();

    Float getInSpeedPerSecond();

    Float getOutSpeedPerSecond();

    long getAllThroughCount();


    void startCalculate();

    void stopCalculate();

    /**
     * 设置是否计算速度标志位；true：计算；false：不计算；
     *
     * @param calculateFlag
     */
    void setCalculateFlag(boolean calculateFlag);

    boolean isCalculateFlag();

    List<DataChannelDispatcher<E, ? extends E>> getChannelDispatchers();

    void setChannelDispatchers(List<DataChannelDispatcher<E, ? extends E>> channelDispatchers);

    default void setSpeedCycle(int cycle) {
    }

    default int getSpeedCycle() {
        return 0;
    }


}
