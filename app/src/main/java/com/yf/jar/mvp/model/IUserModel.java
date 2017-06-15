package com.yf.jar.mvp.model;

import com.yf.jar.mvp.UserBean;

/**
 * 类功能：
 * Created by lenovo on 2017/6/1 20:45.
 */
public interface IUserModel {

    int getID();
    void setName();
    UserBean load(int id);
}
