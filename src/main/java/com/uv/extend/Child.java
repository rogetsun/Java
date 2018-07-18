package com.uv.extend;

/**
 * @author uvsun 2018/7/18 下午2:40
 */
public class Child extends Father {


    @Override
    public void say() {
        System.out.println("child say");
        this.chanZi();
    }
}
