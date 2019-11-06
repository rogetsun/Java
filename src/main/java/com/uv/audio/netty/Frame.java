package com.uv.audio.netty;

/**
 * Created by uv2sun on 2017/4/26.
 */
public class Frame {
    public static final int UP_FRAME = 1;
    public static final int DOWN_FRAME = 0;
    public static final int SENSOR_TO_SENSOR = 2;
    public static int FRAME_LEN = 12;
    private String clientIP;
    private byte[] data;
    private Header header;
    private long time = System.currentTimeMillis();


    /**
     * 1：上发给监控系统，0：监控下发给设备，2：设备发送给设备
     **/
    private int isUpDown;


    /**
     * 接收使用构造方法
     */
    public Frame(byte[] frameData) throws Exception {
        if (frameData.length != CANDict.CAN_FRAME_LEN) {
            throw new Exception("数据格式长度不正确");
        }
        this.header = new Header(BinaryUtil.subArray(frameData, 0, 4));
        this.data = BinaryUtil.subArray(frameData, 4, 8);
    }


    /**
     * 发送使用构造方法
     */
    public Frame(Header header, byte[] data) {
        this.header = header;
        this.data = data;
    }

    public Frame() {
    }

    public Frame generateCRC() {
        this.data[data.length - 1] = (byte) (this.getCRCValue() & 0xff);
        return this;
    }

    public Boolean checkCRC() {//校验规则没定，认为都正确
        int check = getCRCValue();
        if ((data[data.length - 1] & 0xff) == (check & 0xff)) {//校验累加和
            return true;
        }
        return false;
    }

    public byte[] getFrameByte() {
        return BinaryUtil.append(this.header.getHeadByte(), this.data);
    }

    private int getCRCValue() {
        int check = 0;
        byte[] frameByte = this.getFrameByte();
        for (int i = 0; i < frameByte.length - 1; i++) {//不要累加和那个字节
            check += (frameByte[i] & 0xff);
        }
        return check;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public int getIsUpDown() {
        return isUpDown;
    }

    public void setIsUpDown(int isUpDown) {
        this.isUpDown = isUpDown;
    }

    @Override
    public String toString() {
        return header +
                ",data=" + BinaryUtil.bytesToHexString(data);
    }


    /**
     * 提取can帧body部分8字节byte数组
     *
     * @return
     */
    public byte[] getBodyBytes() {
        return this.getData();
    }

    public long getCreateTimestamp() {
        return this.time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Frame) {
            Frame f = (Frame) obj;
            return this.getHeader().getCmd() == f.getHeader().getCmd()
                    && this.getHeader().getKind() == f.getHeader().getKind()
                    && this.getHeader().getIdmac() == f.getHeader().getIdmac()
                    && this.getCreateTimestamp() == f.getCreateTimestamp();
        } else {
            return false;
        }
    }


    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

}
