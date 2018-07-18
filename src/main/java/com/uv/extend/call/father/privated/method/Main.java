package com.uv.extend.call.father.privated.method;

import com.uv.extend.Child;
import com.uv.extend.Father;

/**
 * @author uvsun 2018/7/18 下午2:52
 */
public class Main {
    public static void main(String[] args) {
        Father f = new Father();
        f.setId(1);

        Child c = new Child();
        c.setId(2);

        f.say();
        c.say();
    }
}
