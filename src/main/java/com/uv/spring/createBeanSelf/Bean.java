package com.uv.spring.createBeanSelf;

/**
 * @author uvsun 2018/1/2 下午12:38
 */
public class Bean {
    private int id;
    private String name;

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

    @Override
    public String toString() {
        return "Bean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
