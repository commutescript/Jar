package com.yf.jar.msg;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yf.jar.R;
import com.yf.jar.constants.Constant;

public class MessengerActivity extends Activity {

    private static  final String TAG="MessengerActivity";

    private Messenger messenger;

    private static  class mHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constant.MSG_FROM_SERVICE:
                    Log.i(TAG,"从服务取得数据："+msg.getData().getString("reply"));
                    break;
                default:
                    super.handleMessage(msg);

            }

        }
    }

    private Messenger mGetReplyMsg = new Messenger(new mHandler());




    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            messenger = new Messenger(iBinder);
            Message msg = Message.obtain(null, Constant.MSG_FROM_ClIENT);
            Bundle data = new Bundle();
            data.putString("msg","这是Messenger");
            msg.setData(data);
            Log.i(TAG,"消息发送成功");
            msg.replyTo=mGetReplyMsg;

            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        Intent mIntent = new Intent(this,MessengerService.class);
        bindService(mIntent,mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        super.onDestroy();
    }
}
