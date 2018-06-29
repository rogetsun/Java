package com.uv.spring.aop;

import com.uv.spring.aop.annotation.Limit;
import com.uv.spring.aop.annotation.ReceiveCmd;
import com.uv.spring.aop.bean.Bean;
import com.uv.spring.aop.bean.Cmd;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author uvsun 2018/6/29 下午12:05
 */
public class Main2 {
    public static void main(String[] args) throws Throwable {
        String[] xmlFiles = {"spring-config-aop.xml"};
        ApplicationContext ac = new ClassPathXmlApplicationContext(xmlFiles);
        Bean bean = ac.getBean("Bean", Bean.class);
        Bean realBean = UVAopUtils.getTarget(bean);
        System.out.println("proxyObject:" + bean.hashCode());
        System.out.println("orginObject" + realBean.hashCode());
        System.out.println(AopUtils.isAopProxy(bean));
        System.out.println(AopUtils.isCglibProxy(bean));
        Cmd c = new Cmd();
        c.setId(1);

        bean.receiveCmd(c);

        System.out.println("*******");
        Method m = bean.getClass().getMethod("dealCmd", Cmd.class);
        System.out.println(m);
        System.out.println(m.getAnnotation(Limit.class));
        System.out.println(m.getAnnotation(ReceiveCmd.class));
        System.out.println(AopProxyUtils.getSingletonTarget(bean));
        System.out.println(Arrays.toString(AopProxyUtils.proxiedUserInterfaces(bean)));
    }
}
