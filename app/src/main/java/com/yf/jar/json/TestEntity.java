package com.yf.jar.json;

import java.io.Serializable;

/**
 * 类功能：
 * Created by lenovo on 2017/5/10 15:50.
 */
public class TestEntity implements Serializable{

    private static final long serialVersionUID=1L;


    public TestEntity() {
        super();
    }

    public TestEntity(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

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
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
