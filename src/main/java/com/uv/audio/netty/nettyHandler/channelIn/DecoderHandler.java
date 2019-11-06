package com.uv.audio.netty.nettyHandler.channelIn;

import com.uv.audio.netty.BinaryUtil;
import com.uv.audio.netty.CANDict;
import com.uv.audio.netty.Frame;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.List;


/**
 * Created by clark on 2017/3/28.
 */
public class DecoderHandler extends ByteToMessageDecoder {
    private static final Log log = LogFactory.getLog(DecoderHandler.class);
    private int HeadTailLen = CANDict.PROTOCOL_HEAD.length;
    private ByteBuf HeadBuf = Unpooled.copiedBuffer(CANDict.PROTOCOL_HEAD);
    private ByteBuf TailBuf = Unpooled.copiedBuffer(CANDict.PROTOCOL_TAIL);

    @Override
    protected final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        byte[] decoded = this.decode(in);
        if (decoded != null) {
            try {
                Frame frame = new Frame(decoded);
                out.add(frame);
            } catch (Exception e) {
                log.error("decode frame error，bytes:" + Arrays.toString(decoded), e);
            }
        }
    }

    protected byte[] decode(ByteBuf buffer) throws Exception {
//            log.debug("Netty:Buffer:receive:" + ByteBufUtil.prettyHexDump(buffer).replaceAll("\\n", "\n Netty: receive buffer:\t"));
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
                if (out != null) {
                    return out;
                } else {
                    log.error("校验和有问题或帧格式错误," + ByteBufUtil.prettyHexDump(message));
                    return null;
                }
            }
        }
        log.debug("帧信息不全，没头或没尾");
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
            log.error("数据格式有问题：" + ByteBufUtil.prettyHexDump(in), e);
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

}
