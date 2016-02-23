package net.doubov.myredditclient.di;

import android.database.sqlite.SQLiteDatabase;


import net.doubov.myredditclient.App;
import net.doubov.myredditclient.model.DaoMaster;
import net.doubov.myredditclient.model.DaoSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GreenDaoModule {

  @Provides
  @Singleton
  /** WARNING: Drops all table on Upgrade! Use only during development. */
  DaoMaster.OpenHelper provideDevOpenHelper(App app) {
    return new DaoMaster.DevOpenHelper(app, null, null);
  }

  @Provides
  @Singleton
  DaoMaster provideDaoMaster(DaoMaster.OpenHelper dbOpenHelper) {
    SQLiteDatabase writableDb = dbOpenHelper.getWritableDatabase();
    return new DaoMaster(writableDb);
  }

  @Provides
  @Singleton
  DaoSession provideDaoSession(DaoMaster daoMaster) {
    return daoMaster.newSession();
  }
}
