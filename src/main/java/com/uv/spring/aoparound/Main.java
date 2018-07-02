package com.uv.spring.aoparound;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author uvsun 2018/7/2 下午3:12
 */
public class Main {
    public static void main(String[] args) {
        String[] s = {"spring-config-aop-around.xml"};
        ApplicationContext ac = new ClassPathXmlApplicationContext(s);
        Bean bean = ac.getBean(Bean.class);
        System.out.println(bean);
        bean.m1("name1");
        String r = bean.m2("name2", 2);
        System.out.println(r);

    }
}
