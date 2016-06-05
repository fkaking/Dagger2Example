package com.fkaking.daggerexample;

import android.app.Application;
import android.content.Context;

import com.fkaking.daggerexample.component.AppComponent;
import com.fkaking.daggerexample.component.DaggerAppComponent;
import com.fkaking.daggerexample.component.DaggerGithubComponent;
import com.fkaking.daggerexample.component.DaggerNetComponent;
import com.fkaking.daggerexample.component.GithubComponent;
import com.fkaking.daggerexample.component.NetComponent;
import com.fkaking.daggerexample.module.AppModule;
import com.fkaking.daggerexample.module.NetModule;

public class MyApplication extends Application {
    private static final String appName = "Dagger2Example";
    private AppComponent appComponent;
    private NetComponent netComponent;
    private GithubComponent gitHubComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        netComponent = DaggerNetComponent.builder().appModule(new AppModule(this)).netModule(new NetModule("https://api.github.com")).build();
        gitHubComponent = DaggerGithubComponent.builder().netComponent(netComponent).build();
    }

    public String getAppName() {
        return appName;
    }

    public static AppComponent getAppCompontent(Context context) {
        return ((MyApplication) context.getApplicationContext()).appComponent;
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

    public GithubComponent getGitHubComponent() {
        return gitHubComponent;
    }
}
