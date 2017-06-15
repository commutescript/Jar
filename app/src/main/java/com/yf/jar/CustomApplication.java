package com.yf.jar;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.yf.jar.db.gen.DaoMaster;
import com.yf.jar.db.gen.DaoSession;
import com.yf.jar.db.gen.UserDao;

import org.greenrobot.greendao.database.Database;

import butterknife.ButterKnife;

/**
 * 类功能：
 * Created by lenovo on 2017/5/23 18:58.
 */
public class CustomApplication extends Application{

    private AppComponent appComponent;
    private static DaoSession daoSession;


    public static CustomApplication get(Context context){
        return (CustomApplication)context.getApplicationContext();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
        Stetho.initializeWithDefaults(this);
        appComponent=DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule())
                .build();
        setupDB();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void  setupDB() {
        if (daoSession==null) {
            DaoMaster.OpenHelper openHelper = new DaoMaster.OpenHelper(this, "test"){
                @Override
                public void onUpgrade(Database db, int oldVersion, int newVersion) {
                    switch (oldVersion) {
                        case 1:
                            db.beginTransaction();
                            db.execSQL("...");
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            break;
                    }
                }
            };
            SQLiteDatabase db = openHelper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }
    }

    public static UserDao getUserDao() {
        return daoSession.getUserDao();
    }
}
