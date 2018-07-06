package com.uv.spring.parent.get.child.aopproxy;

import com.uv.spring.aop.bean.Cmd;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author uvsun 2018/7/4 上午9:21
 */
public class Main {
    public static void main(String[] args) {

        BeanFactory ac = new ClassPathXmlApplicationContext(new String[]{"sc-parent-get-child-aopproxy.xml"});
        Parent bean1 = ac.getBean("Me", Parent.class);
        Parent bean2 = ac.getBean("Son", Parent.class);
        Cmd c1 = new Cmd();
        c1.setId(1);
        Cmd c2 = new Cmd();
        c2.setId(2);
        System.out.println();
        bean1.receive(c1);
        System.out.println();
        System.out.println("bean1 deal : " + c1);
        System.out.println();
        bean2.receive(c2);
        System.out.println();
        System.out.println("bean2 deal : " + c2);
        System.out.println();

        Map<Object, Integer> m = new ConcurrentHashMap<>();
        m.put(null, 1);

        System.out.println(m);
    }
}
