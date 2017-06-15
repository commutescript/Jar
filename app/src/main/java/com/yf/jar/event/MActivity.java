package com.yf.jar.event;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.yf.jar.R;

//import org.greenrobot.eventbus.EventBus;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MActivity extends AppCompatActivity {

    @BindView(R.id.eventclick) TextView mTextView;
    String message ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.eventclick) public void submit(){
        message = "default message";
        EventBus.getDefault().post(new MessageEvent(message));
        Toast.makeText(this,"发送了",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,EventActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
