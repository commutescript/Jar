package com.yf.jar.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BinderPoolService extends Service {

    private static final String TAG="BinderPoolService";

    private Binder mBinderPool = new BinderPool.BinderPoolImpl();


    public BinderPoolService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinderPool;
    }
}
