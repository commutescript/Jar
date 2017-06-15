package com.yf.jar.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yf.jar.CustomApplication;
import com.yf.jar.R;
import com.yf.jar.mvp.presenter.UserPresenter;
import com.yf.jar.mvp.view.IUserView;

import javax.inject.Inject;

public class MvpActivity extends AppCompatActivity implements IUserView {

    @Inject
    UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
//        mUserPresenter = new UserPresenter( this);
        DaggerUserComponent.builder().appComponent(CustomApplication.get(this).getAppComponent()).userModule(new UserModule(this)).build().inject(this);
//        mUserPresenter.loadUser();
        CustomApplication.get(this).getAppComponent().getHttpService().getData(1);


    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void setName(String s) {

    }
}
