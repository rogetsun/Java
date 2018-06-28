package com.uv.spring.bean.id;

import org.springframework.beans.factory.BeanNameAware;

/**
 * @author uvsun 2017/10/21 下午9:58
 */
public class IdBean implements BeanNameAware {

    private String beanId;

    @Override
    public void setBeanName(String s) {
        this.beanId = s;
    }

    public String getBeanId() {
        return this.beanId;
    }

}
