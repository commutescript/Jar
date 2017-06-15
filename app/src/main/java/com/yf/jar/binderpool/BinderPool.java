package com.yf.jar.binderpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.concurrent.CountDownLatch;

/**
 * 类功能：
 * Created by lenovo on 2017/5/9 18:54.
 */
public class BinderPool {
    private static final String TAG="BinderPool";
    public static final int BINDER_NONE=-1;
    public static final int BINDER_COMPUTE=0;
    public static final int BINDER_SECURITY_CENTER=1;

    private Context mCOntext;
    private IBinderPool mIBinderPool;
    private static volatile BinderPool sInstance;
    private CountDownLatch mCountDownLatch;

    private BinderPool(Context context){
        mCOntext=context.getApplicationContext();
        connectBinderPoolService();

    }

    //单例模式
    public static  BinderPool getsInstance(Context context){
        if (sInstance==null){
            synchronized (BinderPool.class){
                if(sInstance==null){
                    sInstance=new BinderPool(context);
                }
            }
        }
        return sInstance;
    }

    private synchronized void connectBinderPoolService(){
        mCountDownLatch = new CountDownLatch(1);
        Intent service = new Intent(mCOntext,BinderPoolService.class);
        mCOntext.bindService(service,mServiceConnection,Context.BIND_AUTO_CREATE);

        try {
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public IBinder queryBinder(int bindercode){
        IBinder binder = null;
        if (mIBinderPool!=null){
            try {
                binder = mIBinderPool.queryBinder(bindercode);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return binder;


    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mIBinderPool=IBinderPool.Stub.asInterface(iBinder);
            try {
                mIBinderPool.asBinder().linkToDeath(mDeathRecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mCountDownLatch.countDown();


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            mIBinderPool.asBinder().unlinkToDeath(mDeathRecipient,0);
            mIBinderPool=null;
            connectBinderPoolService();

        }
    };


    public static class BinderPoolImpl extends IBinderPool.Stub{

        public BinderPoolImpl(){
            super();
        }

        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {

            IBinder binder = null;
            switch (binderCode){
                case BINDER_SECURITY_CENTER:
                    binder = new SecurityCenterImpl();
                    break;
                case BINDER_COMPUTE:
                    binder = new ComputeImpl();
                    break;
                default:
                    break;
            }


            return binder;
        }
    }



}
