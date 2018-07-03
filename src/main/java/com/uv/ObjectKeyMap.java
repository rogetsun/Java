package com.uv;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author uvsun 2018/7/3 上午10:32
 * map的key是否一样是靠equals方法的
 */
public class ObjectKeyMap {
    public static void main(String[] args) {
        Map<Object, Date> m = new ObjectKeyMap().test();
        System.out.println(m);

    }

    public Map<Object, Date> test() {
        Map<Object, Date> m = new HashMap<>();
        Object k = new Key(1);
        m.put(new Key(1), new Date());
        m.put(new Key(1), new Date());
        m.put(new Key(1), new Date());
        return m;
    }

    class Key {
        private int id;

        public Key(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "id=" + id +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {

            return ((Key) obj).id == this.id;

        }
    }
}
