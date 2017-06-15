package com.yf.jar.jnidemo;

/**
 * 类功能：
 * Created by lenovo on 2017/5/9 22:13.
 */
public class JniTest {

    static
    {
        System.loadLibrary("JNIDEMO");
    }

    public native String test();
}
