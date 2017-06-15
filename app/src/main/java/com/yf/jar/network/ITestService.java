package com.yf.jar.network;

import com.yf.jar.json.*;
import com.yf.jar.json.TestEntity;

import io.reactivex.Observable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 类功能：
 * Created by lenovo on 2017/5/16 20:23.
 */
public interface ITestService {
    @POST("test/{id}")
    Observable<TestEntity> getData(@Path("id") int id);
}
