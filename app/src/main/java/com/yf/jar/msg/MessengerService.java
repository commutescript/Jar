package com.yf.jar.msg;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.yf.jar.constants.Constant;

import java.util.logging.Handler;

public class MessengerService extends Service {

    private static final String TAG="MessengerService";

    private static class MessengerHandler extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constant.MSG_FROM_ClIENT:
                    Log.i(TAG,"从客户端发来的信息："+msg.getData().getString("msg"));
                    Messenger client = msg.replyTo;
                    Message reMessage = Message.obtain(null,Constant.MSG_FROM_SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply","嗯，你的消息我已经收到了，稍后会回复你");
                    reMessage.setData(bundle);

                    try {
                        client.send(reMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;

                default:
                    super.handleMessage(msg);
                    break;

            }

        }
    }

    private final Messenger messenger = new Messenger(new MessengerHandler());

    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return messenger.getBinder();
    }
}
