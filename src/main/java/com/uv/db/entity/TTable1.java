package com.uv.db.entity;

/**
 * @author uvsun 2018/9/12 下午1:13
 */
public class TTable1 {
    private int id;
    private String name;

    public TTable1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TTable1() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
