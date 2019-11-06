package com.uv.audio.dataChannel;

/**
 * @author uvsun 2018/7/6 下午2:13
 */
public abstract class DefaultDataChannelDispatcher<E, T extends E> implements DataChannelDispatcher<E, T> {

    private DataChannel<T> dataChannel;
    private String name;

    @Override
    public DataChannel<T> getDispatcherChannel() {
        return this.dataChannel;
    }

    @Override
    public void setDispatcherChannel(DataChannel<T> dataChannel) {
        this.dataChannel = dataChannel;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
