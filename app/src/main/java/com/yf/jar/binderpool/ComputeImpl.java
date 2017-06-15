package com.yf.jar.binderpool;

import android.os.RemoteException;

/**
 * 类功能：
 * Created by lenovo on 2017/5/9 16:37.
 */
public class ComputeImpl extends ICompute.Stub{

    @Override
    public int add(int a, int b) throws RemoteException {
        return a+b;
    }
}
