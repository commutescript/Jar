package com.yf.jar.mvp;

/**
 * 类功能：
 * Created by lenovo on 2017/6/1 20:43.
 */
public class UserBean {

    private String name;
    private int id;

    public UserBean(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
