package com.yf.jar;

import android.app.Application;


import com.yf.jar.mvp.retrofitService.HttpService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by clevo on 2015/6/9.
 */
@Singleton
@Component(modules = {AppModule.class, RetrofitModule.class})
public interface AppComponent {


    Application getApplication();

    HttpService getHttpService();

}
