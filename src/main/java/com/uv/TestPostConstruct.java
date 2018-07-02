package com.uv;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author uvsun 2018/7/2 下午1:07
 */
@Component
public class TestPostConstruct implements InitializingBean {

    private void init() {
        System.out.println("init");
    }

    @PostConstruct
    private void postConstruct() {
        System.out.println("postConstruct");
    }

    public TestPostConstruct() {
        System.out.println("con");
    }

    public static void main(String[] args) {
        String[] files = {"spring-config-postconstruct.xml"};
        ApplicationContext ac = new ClassPathXmlApplicationContext(files);
        ac.getBean(TestPostConstruct.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }
}
