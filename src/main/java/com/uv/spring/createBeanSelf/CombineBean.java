package com.uv.spring.createBeanSelf;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author uvsun 2018/1/2 下午12:39
 */
@Component("cb")
public class CombineBean {

    @Resource(name = "bean2")
    private Bean bean;


    public Bean getBean() {
        return bean;
    }

    public void setBean(Bean bean) {
        this.bean = bean;
    }

    @Override
    public String toString() {
        return "CombineBean{" +
                "bean=" + bean +
                '}';
    }
}
