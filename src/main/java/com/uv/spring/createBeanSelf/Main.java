package com.uv.spring.createBeanSelf;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author uvsun 2018/01/02 下午9:58
 */
public class Main {
    public static void main(String[] args) {
        String[] configFile = {"spring-config-self.xml"};
        ApplicationContext ac = new ClassPathXmlApplicationContext(configFile);
        System.out.println(ac.getBean("bean1"));
        System.out.println(ac.getBean("bean2"));

        AutowireCapableBeanFactory bf = ac.getAutowireCapableBeanFactory();
        CombineBean cb = bf.createBean(CombineBean.class);
        System.out.println(cb);
        bf.autowireBean(cb);
        System.out.println(cb);
    }
}
