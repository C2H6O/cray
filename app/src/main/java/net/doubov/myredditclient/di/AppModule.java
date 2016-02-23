package net.doubov.myredditclient.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import net.doubov.myredditclient.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

  private App app;

  public AppModule(App app) {
    this.app = app;
  }

  @Provides
  @Singleton
  App provideApp() {
    return app;
  }

  @Provides
  @Singleton
  SharedPreferences provideSharedPrefs(Context context) {
    return PreferenceManager.getDefaultSharedPreferences(context);
  }


}
