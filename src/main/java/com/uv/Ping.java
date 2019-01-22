package com.uv;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author uvsun 2019/1/22 3:21 PM
 */
public class Ping {

    public static void main(String[] args) throws IOException {
        String ip = "192.168.1.204";

        //超时应该在3钞以上
        int timeOut = 3000;
        // 当返回值是true时，说明host是可用的，false则不可。
        boolean status = InetAddress.getByName(ip).isReachable(timeOut);
        System.out.println(status);
    }
}
