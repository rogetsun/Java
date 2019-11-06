package com.uv.audio.netty;


import com.uv.audio.dataChannel.impl.FrameChannel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BaseClientHandler extends ChannelInboundHandlerAdapter {

    private int HeadTailLen = CANDict.PROTOCOL_HEAD.length;
    private ByteBuf HeadBuf = Unpooled.copiedBuffer(CANDict.PROTOCOL_HEAD);
    private ByteBuf TailBuf = Unpooled.copiedBuffer(CANDict.PROTOCOL_TAIL);

    private FrameChannel frameChannel;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf b = (ByteBuf) msg;
        byte[] decoded = this.decode(b);
        if (decoded != null) {
            Frame frame = new Frame(decoded);
            if (frame.getHeader().getCmd() == 32) {
                frameChannel.put(frame);
            }
        }
    }

    protected byte[] decode(ByteBuf buffer) throws Exception {
        int headFlag = indexOf(buffer, HeadBuf);
        if (headFlag >= 0) {
            buffer.skipBytes(headFlag);
            int tailFlag = indexOf(buffer, TailBuf);
            if (tailFlag > 0) {
                boolean isSpecal = false;
                int index = buffer.readerIndex() + tailFlag;
                int length = tailFlag - HeadTailLen;
                if (buffer.getByte(index - 1) == CANDict.PROTOCOL_SWITCH && buffer.getByte(index - 2) != CANDict.PROTOCOL_SWITCH) {
                    length += 1;
                    isSpecal = true;
                }
                buffer.skipBytes(HeadTailLen);
                byte[] data = new byte[length];
                buffer.readBytes(data);
                buffer = isSpecal ? buffer.skipBytes(1) : buffer.skipBytes(HeadTailLen);

                ByteBuf message = Unpooled.buffer(data.length);
                message.writeBytes(data);
                byte[] out = filerAndCheckSum(message);//过滤掉转义直接 crc校验
                return out;
            }
        }
        return null;
    }

    private static int indexOf(ByteBuf haystack, ByteBuf needle) {
        for (int i = haystack.readerIndex(); i < haystack.writerIndex(); ++i) {
            int haystackIndex = i;

            int needleIndex;
            for (needleIndex = 0; needleIndex < needle.capacity() && haystack.getByte(haystackIndex) == needle.getByte(needleIndex); ++needleIndex) {
                ++haystackIndex;
                if (haystackIndex == haystack.writerIndex() && needleIndex != needle.capacity() - 1) {
                    return -1;
                }
            }
            if (needleIndex == needle.capacity()) {
                return i - haystack.readerIndex();
            }
        }
        return -1;
    }

    /**
     * 计算井下交换机增加协议 aa 55 累加和
     *
     * @param in 不带前面头的交换机协议
     * @return 返回 can帧 12字节
     */
    private static byte[] filerAndCheckSum(ByteBuf in) {

        byte[] data = new byte[17];
        try {
            for (int i = 0; i < data.length; i++) {
                byte temp = in.readByte();
                if ((temp & 0xff) == (CANDict.PROTOCOL_SWITCH & 0xff)) {//转义字节处理
                    temp = in.readByte();//跳过转义读后面那个字节
                }
                data[i] = temp;
            }
        } catch (Exception e) {
            return null;
        }
        //校验和处理
        int check = 0;
        for (int i = 0; i < data.length - 1; i++) {//不要累加和那个字节
            check += (data[i] & 0xff);
        }
        if ((data[data.length - 1] & 0xff) == (check & 0xff)) {//校验累加和
            return BinaryUtil.subArray(data, 0, 12);
        } else {
            return null;
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
    }

    public BaseClientHandler(FrameChannel frameChannel) {
        this.frameChannel = frameChannel;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    public FrameChannel getFrameChannel() {
        return frameChannel;
    }

    public void setFrameChannel(FrameChannel frameChannel) {
        this.frameChannel = frameChannel;
    }
}
