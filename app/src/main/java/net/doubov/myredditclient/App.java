package net.doubov.myredditclient;

import android.app.Application;
import android.os.Build;

import net.doubov.myredditclient.di.AppComponent;
import net.doubov.myredditclient.di.AppModule;
import net.doubov.myredditclient.di.DaggerAppComponent;
import net.doubov.myredditclient.di.GreenDaoModule;

import timber.log.Timber;

public class App extends Application {

  private AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }

    appComponent =
        DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .greenDaoModule(new GreenDaoModule())
            .build();
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }
}
