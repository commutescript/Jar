// IPersonInterface.aidl
package com.yf.jar.aidl;

import com.yf.jar.aidl.Person;
import com.yf.jar.aidl.IOnNewPersonArrivedListener;



interface IPersonInterface {

            List<Person> getPersonList();
            void addPerson(in Person person);
            void registerListener(in IOnNewPersonArrivedListener listener);
            void unregisterListener(in IOnNewPersonArrivedListener listener);


}
