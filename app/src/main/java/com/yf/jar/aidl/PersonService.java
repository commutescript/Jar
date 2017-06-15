package com.yf.jar.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class PersonService extends Service {

    private static final String TAG="PersonService";


    private CopyOnWriteArrayList<Person> mPersonList = new CopyOnWriteArrayList<>();
    private AtomicBoolean mIsDestoryed = new AtomicBoolean(false);
//    private CopyOnWriteArrayList<IOnNewPersonArrivedListener> mListenerList = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewPersonArrivedListener> mListenerList = new RemoteCallbackList<>();



    private Binder mBinder = new IPersonInterface.Stub(){

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return mPersonList;
        }

        @Override
        public void addPerson(Person person) throws RemoteException {
            mPersonList.add(person);

        }


        @Override
        public void registerListener(IOnNewPersonArrivedListener listener) throws RemoteException {
//            if(!mListenerList.contains(listener)){
//                mListenerList.add(listener);
//            }else {
//                Log.i(TAG,"已经注册了");
//            }
            mListenerList.register(listener);

        }

        @Override
        public void unregisterListener(IOnNewPersonArrivedListener listener) throws RemoteException {
//            if (mListenerList.contains(listener)){
//                mListenerList.remove(listener);
//                Log.i(TAG,"解注册成功");
//            }else {
//                Log.i(TAG,"没有找到，不能解注册");
//            }
            mListenerList.unregister(listener);


        }
    };

    public PersonService() {
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        int check = checkCallingOrSelfPermission("com.yf.jar.permission.ACCESS_PERSON_SERVICE");
        Log.i(TAG,"权限验证："+(check== PackageManager.PERMISSION_DENIED));
        if (check== PackageManager.PERMISSION_DENIED){
            return null;
        }
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPersonList.add(new Person(1,"yh"));
        mPersonList.add(new Person(2,"cc"));
        new Thread(new ServiceWorker()).start();


    }

    @Override
    public void onDestroy() {
        mIsDestoryed.set(true);
        super.onDestroy();
    }

    private void onNewPersonArrived(Person person) throws RemoteException {
        mPersonList.add(person);
        final int N = mListenerList.beginBroadcast();
        for (int i=0;i<N;i++){
//            IOnNewPersonArrivedListener listener = mListenerList.get(i);
//            listener.onNewPersonArrived(person);
            IOnNewPersonArrivedListener listener = mListenerList.getBroadcastItem(i);
            if (listener!=null){
                try {
                    listener.onNewPersonArrived(person);

                }catch (RemoteException e){
                    e.printStackTrace();
                }

            }
            mListenerList.finishBroadcast();
        }
    }

    private class ServiceWorker implements Runnable{

        @Override
        public void run() {
            while (!mIsDestoryed.get()){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int id = mPersonList.size()+1;
                Person newPerson = new Person(id,"newPerson"+id);
                try {
                    onNewPersonArrived(newPerson);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
