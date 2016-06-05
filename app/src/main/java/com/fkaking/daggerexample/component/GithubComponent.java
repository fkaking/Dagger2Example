package com.fkaking.daggerexample.component;

import com.fkaking.daggerexample.MainActivity;
import com.fkaking.daggerexample.module.GithubModule;
import com.fkaking.daggerexample.scope.UserScope;

import dagger.Component;

@UserScope
@Component(dependencies = NetComponent.class,modules = {GithubModule.class})
public interface GithubComponent {
    public void inject(MainActivity mainActivity);
}
