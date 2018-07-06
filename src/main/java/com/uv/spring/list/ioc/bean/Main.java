package com.uv.spring.list.ioc.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author uvsun 2018/7/6 下午2:53
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring-config-list-ioc-bean.xml"});
        Bean bean = ac.getBean("bean", Bean.class);
        System.out.println(Arrays.toString(bean.getL().toArray()));
    }
}
