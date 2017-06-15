package com.yf.jar.mvp.presenter;

import android.util.Log;

import com.yf.jar.mvp.UserBean;
import com.yf.jar.mvp.model.IUserModel;
import com.yf.jar.mvp.model.UserModelImpl;
import com.yf.jar.mvp.view.IUserView;

import javax.inject.Inject;

/**
 * 类功能：
 * Created by lenovo on 2017/6/1 20:57.
 */
public class UserPresenter {
    private IUserView mUserView;
    private IUserModel mUserModel;
    @Inject
    public UserPresenter(IUserView view) {
        mUserView = view;
        mUserModel = new UserModelImpl();
        Log.i("xixi","依赖注入了presenter");
    }

    public void saveUser( int id, String name) {
        mUserModel.getID();
    }

    public UserBean loadUser( int id) {
        UserBean user = mUserModel.load(id);//可以进行网络请求
//        mUserView.setName(user.getName()); // 通过调用IUserView的方法来更新显示
        return user;
    }
}
