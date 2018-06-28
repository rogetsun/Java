package com.uv.spring.byteBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author uvsun 2017/11/17 下午12:36
 */
public class Main {
    public static void main(String[] args) {
        String[] configFile = {"spring-config.xml"};
        ApplicationContext ac = new ClassPathXmlApplicationContext(configFile);
        BytePropertyBean bean = ac.getBean("bytePropertyBean", BytePropertyBean.class);
        System.out.println(bean);
        System.out.println(Arrays.toString(bean.getBytes()));
    }
}
