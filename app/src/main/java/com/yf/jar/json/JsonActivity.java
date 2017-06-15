package com.yf.jar.json;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bluelinelabs.logansquare.LoganSquare;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.yf.jar.R;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonActivity extends AppCompatActivity {


    private static final String TAG = "JsonActivity";
    private List<TestEntity> mTestEntityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        Gson mGson = new Gson();
        Gson mGson02= new GsonBuilder().create();
        mTestEntityList = new ArrayList<TestEntity>();
        mTestEntityList.add(new TestEntity(1,"你好"));
        mTestEntityList.add(new TestEntity(2,"你好"));
        mTestEntityList.add(new TestEntity(3,"你好"));



        String json1 = mGson.toJson(mTestEntityList);


        try {
            json1= LoganSquare.serialize(mTestEntityList);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String json2={"name":"你好","id":1};
        Type type = new TypeToken<List<TestEntity>>(){}.getType();
        List<TestEntity> mList = mGson.fromJson(json1,type);

        Log.i(TAG,"序列化："+json1);
        Log.i(TAG,"反序列化:"+mList.toString());


        String json2 = JSON.toJSONString(mTestEntityList);

        String json3 = JSON.toJSONString(new TestEntity(4,"我不好"));

        Log.i(TAG,"fastjson序列化："+json3);
        Type type1 = new TypeReference<List<TestEntity>>(){}.getType();

        List<TestEntity> mList02 = JSON.parseArray(json2,TestEntity.class);
        Log.i(TAG,"fastjson反序列化："+mList02.toString());
        List<TestEntity> mList03 = JSON.parseObject(json2,type1);


    }
}
