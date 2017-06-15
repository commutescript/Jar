// ISecurityCenter.aidl
package com.yf.jar.binderpool;

// Declare any non-default types here with import statements

interface ISecurityCenter {

     String encrypt(String content);
     String decrypt(String content);


}
