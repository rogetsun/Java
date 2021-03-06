package com.uv.spring.aop;

import com.uv.spring.aop.bean.Bean;
import com.uv.spring.aop.bean.Cmd;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;

/**
 * @author uvsun 2018/6/28 下午2:16
 */
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String[] appStr = {"spring-config-aop.xml"};
        ApplicationContext app = new ClassPathXmlApplicationContext(appStr);
        Bean bean = app.getBean("Bean", Bean.class);
        Bean bean2 = app.getBean("Bean", Bean.class);
        Cmd cmd = new Cmd();
        cmd.setId(1);
        System.out.println(bean);
        bean.dealCmd(cmd);
        System.out.println("**********************");
        System.out.println(bean2);
        bean2.dealCmd(cmd);
        Bean bean3 = app.getBean("ChildBean1", Bean.class);
        System.out.println("**********************");
        System.out.println(bean3);
        bean3.dealCmd(cmd);

    }
}
