package com.yf.jar.di;

import com.yf.jar.aidl.*;

import dagger.Module;
import dagger.Provides;

/**
 * 类功能：
 * Created by lenovo on 2017/6/5 16:11.
 */
@Module
public class DemoModule {

    @Provides
    Person providePerson(){
     return new Person();
    }
}
