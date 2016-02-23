package net.doubov.myredditclient.di;

import net.doubov.myredditclient.MainActivityFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, GreenDaoModule.class})
public interface AppComponent {

  void inject(MainActivityFragment mainActivityFragment);

}
