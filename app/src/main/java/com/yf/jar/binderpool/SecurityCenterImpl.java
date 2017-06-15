package com.yf.jar.binderpool;

import android.os.RemoteException;

/**
 * 类功能：
 * Created by lenovo on 2017/5/9 16:59.
 */
public class SecurityCenterImpl extends ISecurityCenter.Stub{

    private static final char SECRET_CODE='^';

    @Override
    public String encrypt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for (int i=0;i<chars.length;i++){
            chars[i]^=SECRET_CODE;
        }
        return  new String(chars);
    }

    @Override
    public String decrypt(String content) throws RemoteException {
        return encrypt(content);
    }
}
