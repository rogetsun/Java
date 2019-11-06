package com.uv.audio.netty;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author: fan
 * @date:2017年5月16日 下午3:06:50
 */
public class CmdParseUtil {
    private static final Log log = LogFactory.getLog(CmdParseUtil.class);
    public static DecimalFormat decimalFormat = new DecimalFormat(".00");

    /**
     * @param byteLength byte数组长度
     * @param args       args参数二维数组{参数值，占用字节数}
     * @return
     */
    public static byte[] setByte(int byteLength, Integer[][] args) {
        byte[] frameDataBytes = new byte[byteLength];

        int i = 0;
        for (Integer[] arg : args) {

            //参数占用字节
            int argLength = arg[1];
            for (int j = 0; j < argLength; j++) {
                log.debug(arg[0]);
                frameDataBytes[i + j] = (byte) (arg[0] >> ((argLength - j - 1) * 8) & 0xFF);
            }
            i += argLength;
        }

        return frameDataBytes;

    }

    /**
     * byte数组设置类属性
     * 通过属性占用的字节转换
     *
     * @param src
     * @param offset
     * @param len
     * @return
     */
    public static int bytes2int(byte[] src, int offset, int len) {
        byte[] bArr = BinaryUtil.subArray(src, offset, len);
        return BinaryUtil.byteArr2Int(bArr);
    }

    /**
     * 处理float进度问题,只截取小数后2位.注意不是四舍五入.
     * 20180523重新修改为四舍五入.高老师解决的float的问题.
     *
     * @param f
     * @return
     */
    public static float cutFloat(float f) {
//        return f;
        BigDecimal lowBD = new BigDecimal(f);
//        return lowBD.setScale(2, BigDecimal.ROUND_DOWN).floatValue();
        return lowBD.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 处理float进度问题,只截取小数后2位.注意不是四舍五入.
     * 20180523重新修改为四舍五入.高老师解决的float的问题.
     *
     * @param f
     * @return
     */
    public static BigDecimal cutFloat(BigDecimal f) {
//        return f;
//        return lowBD.setScale(2, BigDecimal.ROUND_DOWN).floatValue();
        return f.setScale(2, BigDecimal.ROUND_HALF_UP);
    }


}
