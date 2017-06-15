package com.yf.jar.di;

import dagger.Component;
import dagger.Module;

/**
 * 类功能：
 * Created by lenovo on 2017/6/5 15:53.
 */
@Component(modules=DemoModule.class)
public interface DemoComponent {

    void inject(Dagger2Activity activity);
}
