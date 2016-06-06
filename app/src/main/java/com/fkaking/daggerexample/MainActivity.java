package com.fkaking.daggerexample;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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
    private EditText nameInput;
    private TextInputLayout nameInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameInput = (EditText) findViewById(R.id.user_name);
        nameInputLayout = (TextInputLayout) findViewById(R.id.inputlayout_name);
        initListener();
        ((MyApplication) getApplication()).getGitHubComponent().inject(this);
    }

    private void initListener() {
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nameInput.getText().toString();
                if(TextUtils.isEmpty(userName)){
                    nameInputLayout.setError("enter user name");
                    return;
                }
                nameInputLayout.setErrorEnabled(false);
                Call<ArrayList<Repository>> call = mGitHubApiInterface.getRepository(userName);
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
