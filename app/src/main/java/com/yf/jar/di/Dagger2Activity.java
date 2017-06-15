package com.yf.jar.di;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yf.jar.R;

import javax.inject.Inject;

public class Dagger2Activity extends AppCompatActivity {

    @Inject
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        //特别注意DaggerCarComponent是Dagger2自动生成的
        DemoComponent component = DaggerDemoComponent.builder().build();
        //进行注入
        component.inject(this);
        Log.i("111",person.toString());
    }
}
