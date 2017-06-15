package com.yf.jar.binderpool;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yf.jar.R;
import com.yf.jar.jnidemo.JniTest;

public class BinderPoolActvity extends AppCompatActivity {

    private JniTest mJniTest;


    private static final String TAG  = "BinderPoolActvity";

    private ISecurityCenter mISecurityCenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool_actvity);
        mJniTest = new JniTest();
        Log.i(TAG,"jni函数调用"+mJniTest.test());
        new Thread(new Runnable() {

            @Override
            public void run() {
                doWork();
            }
        }).start();
    }



    private void doWork() {
        BinderPool binderPool = BinderPool.getsInstance(BinderPoolActvity.this);
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        mISecurityCenter = (ISecurityCenter)SecurityCenterImpl.asInterface(securityBinder);
        String msg = "hello-你好";
        try {
            String psd = mISecurityCenter.encrypt(msg);
            Log.i(TAG,"加密信息："+psd);
            Log.i(TAG,"解密信息："+mISecurityCenter.decrypt(psd));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
