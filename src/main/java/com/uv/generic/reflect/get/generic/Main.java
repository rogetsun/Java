package com.uv.generic.reflect.get.generic;

import java.lang.reflect.Field;

/**
 * @author uvsun 2018/7/6 下午2:43
 */
public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        Field f = Bean.class.getDeclaredField("l");
        System.out.println(f);
        System.out.println(f.getGenericType());
    }
}
