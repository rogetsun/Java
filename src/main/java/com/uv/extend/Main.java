package com.uv.extend;

/**
 * @author uvsun 2018/9/12 下午4:23
 */
public class Main {

    public static void main(String[] args) {
        Father f = new Father();
        Child c = new Child();

        System.out.println(c.getClass().isInstance(Father.class));
        System.out.println(Child.class.isInstance(Father.class));
        System.out.println();
        System.out.println(Father.class.isInstance(c));
        System.out.println(Father.class.isInstance(Child.class));
        System.out.println();
        System.out.println(c instanceof Father);
        System.out.println();
        is(f, Father.class);
    }

    private static void is(Object c, Class<Father> fatherClass) {

        System.out.println(fatherClass.isInstance(c));

    }


}
