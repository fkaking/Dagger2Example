package com.fkaking.daggerexample.module;

import com.fkaking.daggerexample.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class AppModule {
    MyApplication myApplication;

    public AppModule(MyApplication application) {
        this.myApplication = application;
    }

    @Provides
    @Singleton
    MyApplication provideMyApp() {
        return myApplication;
    }
}
