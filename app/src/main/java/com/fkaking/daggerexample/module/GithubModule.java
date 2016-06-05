package com.fkaking.daggerexample.module;

import com.fkaking.daggerexample.network.interfaces.GitHubApiInterface;
import com.fkaking.daggerexample.scope.UserScope;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;

@Module
public class GithubModule {

    @Provides
    @UserScope
    public GitHubApiInterface providesGitHubInterface(Retrofit retrofit) {
        return retrofit.create(GitHubApiInterface.class);
    }
}
