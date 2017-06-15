package com.yf.jar.mvp.retrofitService;

import com.yf.jar.json.TestEntity;
import com.yf.jar.mvp.UserBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 类功能：
 * Created by lenovo on 2017/6/6 10:56.
 */
public interface HttpService {

    @POST("test/{id}")
    Observable<UserBean> getData(@Path("id") int id);
}
