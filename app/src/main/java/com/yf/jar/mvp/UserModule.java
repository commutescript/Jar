package com.yf.jar.mvp;

import com.yf.jar.mvp.view.IUserView;

import dagger.Module;
import dagger.Provides;

/**
 * 类功能：
 * Created by lenovo on 2017/6/5 16:55.
 */
@Module
public class UserModule {

    private final IUserView view ;
    public UserModule(IUserView view){
        this.view = view ;
    }
    @Provides
    IUserView provideILogView(){
        return view;
    }
}
