package com.example.splashlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.splashlogin.API.UserApiService;
import com.example.splashlogin.model.LoginBody;
import com.example.splashlogin.model.LoginResult;
import com.example.splashlogin.rest.AppService;
import com.example.splashlogin.rest.RetrofitUtility;
import com.example.splashlogin.rest.Utility;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class login extends AppCompatActivity {
    EditText username, password;
    private Button buttonLogin;
    private TextView Register;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Utility.askPermission(this);
        retrofit= RetrofitUtility.initialieRetrofit();
        username = findViewById(R.id.editTextTextPersonName2);
        password = findViewById(R.id.editTextTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        Register = findViewById(R.id.Register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, Registration.class);
                startActivity(intent);

            }

        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() == 0) {
                    username.setError("The Username field is required");
                } else if (password.getText().toString().length() == 0) {
                    password.setError("The Password field is required");
                } else {
                    submitLogin(username.getText().toString(), password.getText().toString());
                }
            }
            });


    }

    private void submitLogin(String userName, String password) {
        LoginBody loginBody = new LoginBody(userName, password);
        UserApiService apiService = retrofit.create(UserApiService.class);
        Call<LoginResult> result = apiService.getResultInfo(loginBody);

        result.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                try {
                    if (response.body().isSuccess()) {
                        Log.e("TAG", "Login Success" + response.body().toString());
                        AppService.setToken("Bearer " + response.body().getToken());
                        Intent intent= new Intent(login.this,BookActivity.class);
                        startActivity(intent);
                    } else {
                        Log.e("TAG", "login gagal" + response.body().toString());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }
}