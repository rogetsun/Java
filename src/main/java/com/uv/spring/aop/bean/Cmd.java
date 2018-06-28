package com.uv.spring.aop.bean;

/**
 * @author uvsun 2018/6/28 下午2:21
 */
public class Cmd {
    private int id;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cmd{" +
                "id=" + id +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }
}
