package com.uv.spring.aoparound;

import com.uv.spring.aop.annotation.Limit;

/**
 * @author uvsun 2018/7/2 下午3:08
 */
public class Bean {

    @Limit
    public void m1(String s) {
        System.out.println("M1 called, param:" + s);
    }

    @Limit
    public String m2(String s, int i) {
        System.out.println("M2 called, param:" + s + ", " + i);
        return "m2";
    }

}
