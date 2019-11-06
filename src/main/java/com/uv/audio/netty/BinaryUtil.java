package com.uv.audio.netty;


/**
 * Created by clark on 2016/12/10.
 */
public class BinaryUtil {

    /**
     * byte数组变成int，如果byte数组长度大于4，只取前四个byte
     *
     * @param bArr
     * @return
     */
    public static int byteArr2Int(byte[] bArr) {
        int d = 0;
        int len = bArr.length > 4 ? 4 : bArr.length;
        for (int i = 0; i < len; i++) {
            d = ((d << 8) | (bArr[i] & 0xff));
        }
        return d;
    }

    public static byte[] append(byte[] org, byte[] to) {
        if (org != null) {
            byte[] newByte = new byte[org.length + to.length];
            System.arraycopy(org, 0, newByte, 0, org.length);
            System.arraycopy(to, 0, newByte, org.length, to.length);
            return newByte;
        } else {
            return to;
        }
    }

    public static byte[] float2byte(float f) {
        byte[] res = new byte[4];
        int l = Float.floatToIntBits(f);
        for (int i = 3; i >= 0; i--) {
            res[i] = new Integer(l).byteValue();
            l >>= 8;
        }
        return res;
    }

    /**
     * 获取一个byte[]的子byte[]V
     *
     * @param src
     * @param offset
     * @param len
     * @return
     */
    public static byte[] subArray(byte[] src, int offset, int len) {
        byte[] r = new byte[len];
        System.arraycopy(src, offset, r, 0, len);
        return r;
    }
//    public static byte[] json2byte(JSONArray arr){
//        byte[] b = new byte[arr.size()];
//        for (int i = 0; i < arr.size(); i++) {
//            b[i] = (byte) (0xff & ((int) arr.get(i)));
//        }
//        return b;
//    }

    /**
     * int变几字节Byte数组
     *
     * @param value
     * @param len
     * @return
     */
    public static byte[] int2byte(int value, int len) {
        byte[] data = new byte[len];
        for (int i = 0; i < len; i++) {
            data[i] = (byte) ((value >> (len - i - 1) * 8) & 0xff);
        }
        return data;
    }

    /**
     * int变4字节Byte数组
     *
     * @param l
     * @return
     */
    public static byte[] int2byte(int l) {
        byte[] res = new byte[4];
        for (int i = 3; i >= 0; i--) {
            res[i] = new Integer(l).byteValue();
            l >>= 8;
        }
        return res;
    }

    public static String bytesToHexString(byte[] src) {
        if (src != null) {
            StringBuilder sb = new StringBuilder();
            for (byte b : src) {
                String tmp = Integer.toHexString(b & 0xff).toUpperCase();
                if (tmp.length() == 1) {
                    sb.append("0");
                }
                sb.append(tmp).append(" ");
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    public static String bytesInfo(byte[] data) {
        byte[] src;
        if (data.length > 12) {
            src = BinaryUtil.subArray(data, 2, 12);
        } else {
            src = data;
        }
        StringBuffer sb = new StringBuffer("");
        int kind = (src[0] >> 2) & 0x1;
        if (kind == 0) {// mac
            String cmd = (src[0] & 0x3) + "";
            int mac = ((src[1] << 16) & 0xff0000) + ((src[2] << 8) & 0xff00) + (src[3] & 0xff);
            sb.append("MAC" + cmd + ",mac值:" + mac);
            if (cmd.equals("1")) {
                int flag = src[4] & 0xff;
                if (flag == 0) {
                    sb.append(" 无父节点");
                } else {
                    int fMac = ((src[5] << 16) & 0xff0000) + ((src[6] << 8) & 0xff00) + (src[7] & 0xff);
                    sb.append(" 父Mac:" + fMac);
                }
                int sensorCode = ((src[8] << 8) & 0xff00) + (src[9] & 0xff);
                sb.append("设备类型：" + sensorCode);
                int num = src[10] & 0xff;
                sb.append(" 通道数：" + num);
            }
            if (cmd.equals("2")) {
                int ip = BinaryUtil.byteArr2Int(BinaryUtil.subArray(src, 8, 2));
                sb.append(" 设备IP：" + ip);
            }
            if (cmd.equals("3")) {
                int ip = BinaryUtil.byteArr2Int(BinaryUtil.subArray(src, 4, 2));
                sb.append(" ip:" + ip);
            }
        } else {// ip
            String cmd = (src[1] >> 2 & 0x3f) + "";
            int code = ((src[2] << 8) & 0xff00) + (src[3] & 0xff);
            sb.append("IP" + cmd + ",ip值:" + code + " ");
            if ((src[1] & 0x3) == 2) {
                sb.append("复帧头帧,共" + (src[4] & 0xff) + "帧");
                sb.append(",data:[ " + BinaryUtil.bytesToHexString(BinaryUtil.subArray(src, 5, 6)) + "]");
            } else if ((src[1] & 0x3) == 3) {
                sb.append("复帧,第" + (src[4] & 0xff) + "帧");
                sb.append(",data:[ " + BinaryUtil.bytesToHexString(BinaryUtil.subArray(src, 5, 6)) + "]");
            } else {
                sb.append("单帧,data:[ " + BinaryUtil.bytesToHexString(BinaryUtil.subArray(src, 4, 7)) + "]");
            }
        }
        sb.append(" data:" + BinaryUtil.bytesToHexString(BinaryUtil.subArray(data, 4, 8)));
        return sb.toString();
    }

    //byte转2进制字符串不够8位补0
    public static String getBinaryStrFromByte(byte b) {
        String result = "";
        byte a = b;
        for (int i = 0; i < 8; i++) {
            byte c = a;
            a = (byte) (a >> 1);//每移一位如同将10进制数除以2并去掉余数。
            a = (byte) (a << 1);
            if (a == c) {
                result = "0" + result;
            } else {
                result = "1" + result;
            }
            a = (byte) (a >> 1);
        }
        return result;
    }


    /**
     * 8位累加和计算
     *
     * @param bytes
     * @return
     */
    public static byte sumCheck8Bit(byte[] bytes) {
        byte sum = 0;

        for (byte aByte : bytes) {
            sum += aByte;
        }

        return (byte) (sum & 0xff);
    }

    /**
     * 16进制float字符串转换
     *
     * @param hexStr 16进制的float
     * @return float
     */
    public static float hexStr2Float(String hexStr) {
        return CmdParseUtil.cutFloat(Float.intBitsToFloat(BinaryUtil.byteArr2Int(hexStr2ByteArr(hexStr))));
    }

    public static byte[] hexStr2ByteArr(String hexStr) {
        byte[] bytes = null;
        if (hexStr == null || hexStr.trim().equals("")) {
            bytes = new byte[0];
        } else {
            hexStr = hexStr.replaceAll(" ", "");
            bytes = new byte[hexStr.length() / 2];
            for (int i = 0; i < hexStr.length() / 2; i++) {
                String subStr = hexStr.substring(i * 2, i * 2 + 2);
                bytes[i] = (byte) Integer.parseInt(subStr, 16);
            }
        }
        return bytes;
    }

    public static void main(String[] args) {
        String hexStr = "1C EE 06 53 1A BC 59 3E 01 00 00 00";
        byte[] a = hexStr2ByteArr(hexStr);
        System.out.println(BinaryUtil.bytesToHexString(a));


        String hex = "40 80 00 00";

        System.out.println(hexStr2Float(hex));
//        System.out.println(CmdParseUtil.cutFloat(hexStr2Float(hex)));

////        1a 00 12 35 59 84 23 7a 35 5f 00 6f 08 00 01 00 e7 55 55
////        String b = "1a 00 13 45 59 83 fa 19 35 5e 00 f4 08 00 01 00";
////        String b = "aa aa 1c f8 3e 38 32 27 4d 00 00 00 00 00 08 00 01 00 39 55 55";
////         String b1 = "09 00 00 bb 00 00 00 00 00 00 00 00";//mac1
////        String b1 = "1a 00 00 bb 00 00 00 00 00 00 00 00";//mac2
////        String b1 = "1c 0e 00 13 00 00 00 00 00 00 00 00";//ip1
//
//
////        float a = (float) 5.0;
////        byte[] bbb = float2byte(a);
////        System.out.println(BinaryUtil.bytesToHexString(bbb));
//
//        String[] byteStr = b1.split(" ");
////        System.out.println(Arrays.toString(byteStr));
//        byte[] bytes = new byte[byteStr.length];
//        for (int i = 0; i < byteStr.length; i++) {
//            bytes[i] = Integer.valueOf(byteStr[i], 16).byteValue();
//        }
////        System.out.println(Arrays.toString(bytes));
////        System.out.println("clark");
//        System.out.println(BinaryUtil.bytesInfo(bytes));
////        System.out.println(BinaryUtil.bytesToHexString(bytes));
////        int sum = sumCheck8Bit(bytes);
////        System.out.println(Integer.toHexString(sum & 0xff));
////        System.out.println(0x7a35);
////        System.out.println(Integer.toHexString(13663));
    }


}
