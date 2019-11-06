package com.uv.audio.dataChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author uvsun 2018/5/31 下午3:49
 */
public interface DataChannelDispatcher<E, T extends E> {

    Log log = LogFactory.getLog(DataChannelDispatcher.class);

    /**
     * 判断e是不是满足dispatch的条件
     *
     * @param e dispatch的元素
     * @return true:满足,false:不满足
     */
    boolean isDispatch(E e);

    /**
     * 获取dispatcher给的通道
     *
     * @return 通道
     */
    DataChannel<T> getDispatcherChannel();

    /**
     * 设置dispatcher给的通道
     *
     * @param dataChannel 通道
     */
    void setDispatcherChannel(DataChannel<T> dataChannel);

    /**
     * dispatch 元素e
     *
     * @param e 通道元素
     */
    @SuppressWarnings("unchecked")
    default void dispatch(E e) {
        if (null != this.getDispatcherChannel() && this.isDispatch(e)) {
            try {
                this.getDispatcherChannel().put((T) e);
            } catch (Exception e1) {
                log.error(this.getName() + " dispatch 元素:" + e + " 失败,", e1);
            }
        }
    }

    /**
     * 设置dispatcher的名字
     *
     * @param name 名字
     */
    void setName(String name);

    /**
     * 获取dispatcher的名字
     *
     * @return 名字
     */
    String getName();

}
