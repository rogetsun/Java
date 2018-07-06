package com.uv.spring.list.ioc.bean;

/**
 * @author uvsun 2018/7/6 下午2:50
 */
public class IocBean {
    private String beanName;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public String toString() {
        return "IocBean{" +
                "beanName='" + beanName + '\'' +
                '}';
    }
}
