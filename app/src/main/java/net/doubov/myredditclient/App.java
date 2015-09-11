package net.doubov.myredditclient;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.model.DaoMaster;
import com.example.model.DaoSession;

public class App extends Application {

    private static App sApp;

    private DaoMaster.DevOpenHelper mDbHelper;
    private DaoSession mDbSession;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        mDbHelper = new DaoMaster.DevOpenHelper(this, null, null);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDbSession = daoMaster.newSession();
    }

    public static App getApp() {
        return sApp;
    }

    public DaoMaster.DevOpenHelper getDbHelper() {
        return mDbHelper;
    }

    public DaoSession getDbSession() {
        return mDbSession;
    }
}
