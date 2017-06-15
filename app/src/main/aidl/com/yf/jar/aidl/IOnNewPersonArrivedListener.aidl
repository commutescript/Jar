// IOnNewPersonArrivedListener.aidl
package com.yf.jar.aidl;
import com.yf.jar.aidl.Person;


interface IOnNewPersonArrivedListener {

      void onNewPersonArrived(in Person newPerson);
}
