package com.yf.jar;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

/**
 * 类功能：
 * Created by lenovo on 2017/6/14 20:48.
 */
public interface TypeFactory {

    <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type);

}
