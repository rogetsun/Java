package com.uv.spring.bean.id;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author uvsun 2017/10/21 下午9:58
 */
public class Main {
    public static void main(String[] args) {
        String[] configFile = {"spring-config.xml"};
        ApplicationContext ac = new ClassPathXmlApplicationContext(configFile);
        PrimaryBean b = ac.getBean("idPrimaryBean", PrimaryBean.class);
        for (IdBean idBean : b.getIdBeans()) {
            System.out.println(idBean.getBeanId());
        }

    }
}
