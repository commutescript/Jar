package com.yf.jar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Messenger;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.yf.jar.aidl.PersonActivity;
import com.yf.jar.binderpool.BinderPoolActvity;
import com.yf.jar.db.DAOActivity;
import com.yf.jar.di.Dagger2Activity;
import com.yf.jar.event.EventActivity;
import com.yf.jar.imageloader.ImageActivity;
import com.yf.jar.json.JsonActivity;
import com.yf.jar.msg.MessengerActivity;
import com.yf.jar.mvp.MvpActivity;
import com.yf.jar.network.NetWorkActivity;
import com.yf.jar.v4v7.RecyclerviewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用butterknife进行数据绑定
        ButterKnife.bind(this);

    }

    //基础控件
    @OnClick({R.id.recycle_view})
    public void goBase(View view){
        switch (view.getId()){
            case R.id.recycle_view:
                mIntent = new Intent(this, RecyclerviewActivity.class);
                startActivity(mIntent);
                break;


        }
    }

    //高级知识点点击事件
    @OnClick({R.id.aidl,R.id.binderpool,R.id.jni,R.id.messenger,R.id.mvp})
    public void goHighActivity(View view){
        switch (view.getId()){
            //aidl
            case R.id.aidl:
                mIntent = new Intent(this, PersonActivity.class);
                startActivity(mIntent);
                break;
            //binder池
            case R.id.binderpool:
                mIntent = new Intent(this, BinderPoolActvity.class);
                startActivity(mIntent);
                break;
            //jni
            case R.id.jni:
                mIntent = new Intent(this, BinderPoolActvity.class);
                startActivity(mIntent);
                break;
            //msg
            case R.id.messenger:
                mIntent = new Intent(this, MessengerActivity.class);
                startActivity(mIntent);
                break;
            //mvp
            case R.id.mvp:
                mIntent = new Intent(this, MvpActivity.class);
                startActivity(mIntent);
                break;

        }

    }

    //第三方框架点击事件
    @OnClick({R.id.db,R.id.di,R.id.json,R.id.net,R.id.img,R.id.event})
    public void goActivity(View view){
        switch (view.getId()){
            //dao
            case R.id.db:
                mIntent = new Intent(this, DAOActivity.class);
                startActivity(mIntent);
                break;
            //di
            case R.id.di:
                mIntent = new Intent(this, Dagger2Activity.class);
                startActivity(mIntent);
                break;
            //json
            case R.id.json:
                mIntent = new Intent(this, JsonActivity.class);
                startActivity(mIntent);
                break;
            //net
            case R.id.jni:
                mIntent = new Intent(this, NetWorkActivity.class);
                startActivity(mIntent);
                break;
            //img
            case R.id.img:
                mIntent = new Intent(this, ImageActivity.class);
                startActivity(mIntent);
                break;
            //event
            case R.id.mvp:
                mIntent = new Intent(this, EventActivity.class);
                startActivity(mIntent);
                break;

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
