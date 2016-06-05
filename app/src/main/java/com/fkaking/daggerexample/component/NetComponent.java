package com.fkaking.daggerexample.component;

import android.content.SharedPreferences;

import com.fkaking.daggerexample.MyApplication;
import com.fkaking.daggerexample.module.AppModule;
import com.fkaking.daggerexample.module.NetModule;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Component;
import retrofit.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();
//    OkHttpClient okHttpClient();
//    SharedPreferences sharedPreferences();
//    MyApplication application();
}
