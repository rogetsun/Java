package com.uv.spring.aop.bean;

/**
 * @author uvsun 2018/6/28 下午2:21
 */
public class Cmd {
    private int id;
    private boolean isOK;
    private String dealer;

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public boolean isOK() {
        return isOK;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cmd{" +
                "id=" + id +
                ", isOK=" + isOK +
                ", dealer='" + dealer + '\'' +
                '}';
    }
}
