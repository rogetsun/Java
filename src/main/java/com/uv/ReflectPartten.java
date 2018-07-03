package com.uv;

import java.lang.reflect.Method;

/**
 * @author uvsun 2018/7/3 上午9:51
 */
public class ReflectPartten<T> {

    public static void main(String[] args) throws NoSuchMethodException {
        new ReflectPartten().run();
    }

    public void run() throws NoSuchMethodException {
        ReflectPartten<N> p = new ReflectPartten<>();
        Method m = p.getClass().getMethod("test");
        N t = p.a();
        System.out.println(t);
    }


    private T a() {
        Object o = this.test();

        return (T) o;
    }


    public Object test() {
        return nn();
    }

    public N nn() {
        return new N(1);
    }


    public class N {
        private int id;

        public N(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "N{" +
                    "id=" + id +
                    '}';
        }
    }

}
