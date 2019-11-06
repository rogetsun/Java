package com.uv.audio.netty;

/**
 * Created by uv2sun on 2017/4/26.
 */
public class Header {

    private static final int DEFAULT_PRIORITY = 0;
    private int priority;//主从00 紧急01 对等10 自主11
    private int kind;//0 mac 1 ip
    private int cmd;//命令int值
    private int idmac;//id号 or mac号
    private int singleOrMultiFrame;//单帧00 = 0  扩展	01 = 1 复帧的头帧	10 = 2 复帧其他帧	11 = 3
    private int relationSensorIdMac;

    //发送使用构造方法
    //默认单帧
    public Header(int kind, int cmd, int idmac) {
        this(DEFAULT_PRIORITY, kind, cmd, idmac, 0);
    }

    //复帧
    public Header(int kind, int cmd, int idmac, int singleOrMultiFrame) {
        this(DEFAULT_PRIORITY, kind, cmd, idmac, singleOrMultiFrame);
    }

    public Header(int priority, int kind, int cmd, int idmac, int singleOrMultiFrame) {
        this.priority = priority;
        this.kind = kind;
        this.cmd = cmd;
        this.idmac = idmac;
        this.singleOrMultiFrame = singleOrMultiFrame;
    }

    //接收使用构造方法
    public Header(byte[] headByte) {
        this.priority = (headByte[0] >> 3) & 0x3;
        if (((headByte[0] >> 2) & 0x1) == CANDict.CAN_FRAME_MAC) {// mac类
            this.cmd = headByte[0] & 0x3;
            this.kind = CANDict.CAN_FRAME_MAC;
            this.idmac = ((headByte[1] << 16) & 0xff0000) + ((headByte[2] << 8) & 0xff00) + (headByte[3] & 0xff);
            this.singleOrMultiFrame = CANDict.CAN_SINGLE_FRAME;
        } else {// ip类
            this.cmd = headByte[1] >> 2 & 0x3f;
            this.kind = CANDict.CAN_FRAME_ID;
            this.idmac = ((headByte[2] << 8) & 0xff00) + (headByte[3] & 0xff);
            this.singleOrMultiFrame = headByte[1] & 0x3;
        }
    }

    public byte[] getHeadByte() {
        //验证属性是否合理
        byte[] headByte = new byte[4];
        if (this.kind == CANDict.CAN_FRAME_MAC) {
            headByte[0] = (byte) (((this.priority & 0x3) << 3) + ((CANDict.CAN_FRAME_MAC & 0x1) << 2) + ((this.cmd & 0x3)));
            headByte[1] = (byte) ((this.idmac >> (2 * 8)) & 0xff);
            headByte[2] = (byte) ((this.idmac >> 8) & 0xff);
            headByte[3] = (byte) (this.idmac & 0xff);
        } else {
            headByte[0] = (byte) (((this.priority & 0x3) << 3) + ((CANDict.CAN_FRAME_ID & 0x1) << 2));
            headByte[1] = (byte) (((this.cmd & 0x3f) << 2) + ((this.singleOrMultiFrame & 0x3)));//单复帧
            headByte[2] = (byte) ((this.idmac >> 8) & 0xff);
            headByte[3] = (byte) (this.idmac & 0xff);
        }
        return headByte;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getIdmac() {
        return idmac;
    }

    public void setIdmac(int idmac) {
        this.idmac = idmac;
    }

    @Override
    public String toString() {
        return "kind=" + kind +
                ", singleOrMultiFrame=" + singleOrMultiFrame +
                ", cmd=" + cmd +
                ", idmac=" + idmac +
                ", rId=" + relationSensorIdMac;
    }

    public int getSingleOrMultiFrame() {
        return singleOrMultiFrame;
    }

    public void setSingleOrMultiFrame(int singleOrMultiFrame) {
        this.singleOrMultiFrame = singleOrMultiFrame;
    }

    public int getRelationSensorIdMac() {
        return relationSensorIdMac;
    }

    public void setRelationSensorIdMac(int relationSensorIdMac) {
        this.relationSensorIdMac = relationSensorIdMac;
    }
}
