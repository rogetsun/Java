package com.uv.multiaop;

import com.uv.db.entity.TTable1;
import com.uv.db.service.TTable1Service;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author uvsun 2018/9/12 下午1:59
 */
public class Main {
    public static void main(String[] args) {
        String[] xml = {"spring-config-multi-aop.xml"};
        ApplicationContext ac = new ClassPathXmlApplicationContext(xml);
        TTable1Service service = ac.getBean(TTable1Service.class);

        System.out.println(service);
        System.out.println(AopUtils.isAopProxy(service));

        int c = service.deleteAll();
        System.out.println("delete count:" + c);

        service.batchAdd(1, 10);

        TTable1 t = new TTable1();
        System.out.println();


    }
}
