package com.yf.jar.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yf.jar.R;
import com.yf.jar.constants.Constant;

import java.util.List;

public class PersonActivity extends Activity {



    private IPersonInterface mIPersonManger;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constant.MSG_NEW_PERSON_ARRIVED:
                    Log.i(TAG,"新人："+((Person)msg.obj).getName());
                    break;
                default:
                    super.handleMessage(msg);

            }

        }
    };


    private static final String TAG="PersonActivity";
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IPersonInterface mIPersonInterface = IPersonInterface.Stub.asInterface(iBinder);
            try {
                mIPersonManger=mIPersonInterface;
                List<Person> list = mIPersonInterface.getPersonList();

                Log.i(TAG,"包名："+list.getClass().getCanonicalName());
                Log.i(TAG,"数据查询:"+list.get(0).getName());
                mIPersonInterface.registerListener(mIOnNewPersonArrivedListener);
                mIPersonManger.asBinder().linkToDeath(mDeathRecipient,0);

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mIPersonManger=null;


        }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if(mIPersonManger==null){
                return;

            }
            mIPersonManger.asBinder().unlinkToDeath(mDeathRecipient,0);
            mIPersonManger=null;
            Intent mIntent = new Intent(PersonActivity.this,PersonService.class);
            bindService(mIntent,mConnection, Context.BIND_AUTO_CREATE);

        }
    };

    private IOnNewPersonArrivedListener mIOnNewPersonArrivedListener = new IOnNewPersonArrivedListener.Stub(){

        @Override
        public void onNewPersonArrived(Person newPerson) throws RemoteException {
            mHandler.obtainMessage(Constant.MSG_NEW_PERSON_ARRIVED,newPerson).sendToTarget();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Intent mIntent = new Intent(this,PersonService.class);
        bindService(mIntent,mConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {

        if(mIPersonManger!=null&&mIPersonManger.asBinder().isBinderAlive()){
            try {
                Log.i(TAG,"解注册："+mIOnNewPersonArrivedListener);
                mIPersonManger.unregisterListener(mIOnNewPersonArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mConnection);
        super.onDestroy();
    }
}
