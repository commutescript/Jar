package com.yf.jar.mvp;

import com.yf.jar.AppComponent;

import javax.inject.Scope;

import dagger.Component;

/**
 * 类功能：
 * Created by lenovo on 2017/6/5 16:57.
 */
@ActivityScope
@Component(modules = UserModule.class,dependencies = AppComponent.class)
public interface UserComponent {
    public void inject(MvpActivity activity);
}
