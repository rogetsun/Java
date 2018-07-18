package com.uv.extend;

/**
 * @author uvsun 2018/7/18 下午2:39
 */
public class Father {

    private int id;

    private void born() {
        System.out.println("father born");
    }

    protected void chanZi() {
        System.out.println("Father chanZi");
        this.born();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void say() {
        System.out.println("say");
        this.chanZi();
    }
}
