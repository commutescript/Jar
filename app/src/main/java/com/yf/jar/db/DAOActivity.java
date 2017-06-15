package com.yf.jar.db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yf.jar.CustomApplication;
import com.yf.jar.R;
import com.yf.jar.db.gen.UserDao;

public class DAOActivity extends AppCompatActivity {

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao);
        userDao = CustomApplication.getUserDao();
        //然后可以通过userdao对数据库进行增删改查操作

    }
}
