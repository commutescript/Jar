//
// Created by lenovo on 2017/5/10.
//

#include "com_yf_jar_jnidemo_JniTest.h"

JNIEXPORT jstring JNICALL Java_com_yf_jar_jnidemo_JniTest_test(JNIEnv *env, jobject obj)
{
    return env -> NewStringUTF("Hello World!");
}

