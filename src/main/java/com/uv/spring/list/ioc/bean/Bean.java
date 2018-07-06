package com.uv.spring.list.ioc.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uvsun 2018/7/6 下午2:49
 */
public class Bean {
    private List<IocBean> l = new ArrayList<>();

    public List<IocBean> getL() {
        return l;
    }

    public void setL(List<IocBean> l) {
        this.l = l;
    }
}
