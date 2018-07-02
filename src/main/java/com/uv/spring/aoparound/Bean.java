package com.uv.spring.aoparound;

import com.uv.spring.aop.annotation.Limit;

/**
 * @author uvsun 2018/7/2 下午3:08
 */
public class Bean {

    @Limit
    public void m1() {
        System.out.println("M1 called");
    }

    @Limit
    public String m2() {
        System.out.println("M2 called");
        return "m2";
    }

}
