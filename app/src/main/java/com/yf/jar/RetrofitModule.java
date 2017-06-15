package com.yf.jar;

import android.app.Application;

import com.yf.jar.mvp.retrofitService.HttpService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类功能：
 * Created by lenovo on 2017/6/6 10:58.
 */
@Module
public class RetrofitModule {

    @Provides
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
//        okHttpClient.setConnectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
//        okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        return okHttpClient;
    }

    @Provides
    public Retrofit provideRetrofit(Application application, OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    protected HttpService provideGitHubService(Retrofit retrofit) {

        return retrofit.create(HttpService.class);
    }
}
