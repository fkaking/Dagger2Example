package com.fkaking.daggerexample;

import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fkaking.daggerexample.component.AppComponent;
import com.fkaking.daggerexample.model.Repository;
import com.fkaking.daggerexample.network.interfaces.GitHubApiInterface;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
//    @Inject
//    MyApplication myApplication;
//    @Inject
//    SharedPreferences mSharedPreferences;
//    @Inject
//    Retrofit mRetrofit;
    @Inject
    GitHubApiInterface mGitHubApiInterface;
    private TextView retrieveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrieveBtn = (TextView) findViewById(R.id.retrieve);
        initListener();
        ((MyApplication) getApplication()).getGitHubComponent().inject(this);
    }

    private void initListener() {
        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ArrayList<Repository>> call = mGitHubApiInterface.getRepository("xingshulin");
                call.enqueue(new Callback<ArrayList<Repository>>() {
                    @Override
                    public void onResponse(Response<ArrayList<Repository>> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            ArrayList<Repository> repositories = response.body();
                            for (Repository repository : repositories) {
                                Log.i("DEBUG", repository.getName() + "---" + repository.getFullName() + "---" + repository.getDescription());
                            }
                        } else {
                            Log.i("ERROR", String.valueOf(response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        });
    }
}
