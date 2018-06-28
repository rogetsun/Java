package com.uv.spring.bean.propertyOverride;

import com.uv.spring.bean.propertyOverride.UvBean;
import com.uv.spring.bean.propertyOverride.UvComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author uvsun 2017/10/21 下午3:37
 */
public class Main {
    public static void main(String[] args) {
        //这里可以有多个xml配置文件,逗号隔开
        String[] configFile = {"spring-config.xml"};
        ApplicationContext ac = new ClassPathXmlApplicationContext(configFile);
        System.out.println(ac);

        UvComponent component = ac.getBean("component", UvComponent.class);
        UvComponent component1 = ac.getBean("componentChild", UvComponent.class);
        System.out.println(component.getBean());
        System.out.println(component1.getBean());
        System.out.println(ac.getBeansOfType(UvBean.class).size());

    }
}
