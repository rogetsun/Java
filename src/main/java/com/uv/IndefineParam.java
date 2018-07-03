package com.uv;

import java.util.Arrays;

/**
 * @author uvsun 2018/7/3 上午9:42
 */
public class IndefineParam {
    public static void test(String... strings) {
        System.out.println(Arrays.toString(strings));
    }

    public static void main(String[] args) {
        test("A", "B");
        String[] strings = {"C", "D"};
        test(strings);
    }


}
