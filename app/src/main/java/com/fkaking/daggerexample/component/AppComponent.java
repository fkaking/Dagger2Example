package com.fkaking.daggerexample.component;

import com.fkaking.daggerexample.MainActivity;
import com.fkaking.daggerexample.MyApplication;
import com.fkaking.daggerexample.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component (modules = {AppModule.class})
public interface AppComponent {
    MyApplication getMyApp();
}
