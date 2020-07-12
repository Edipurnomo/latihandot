package com.example.splashlogin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.splashlogin.API.UserApiService;
import com.example.splashlogin.model.SignUpBody;
import com.example.splashlogin.model.SignUpResult;
import com.example.splashlogin.rest.RetrofitUtility;
import com.example.splashlogin.rest.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Registration extends AppCompatActivity {


        EditText username, email, password, name;
        RadioButton ButtonReg, Button_Reg;
        //Spiner roles;
        Spinner listItem;
        Button button;
        private Retrofit retrofit;
        private boolean rbaktif;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            Utility.askPermission(this);
            retrofit= RetrofitUtility.initialieRetrofit();

            username = findViewById(R.id.username);
            password = findViewById(R.id.password);
            listItem = findViewById(R.id.listItem);
            email = findViewById(R.id.email);
            name = findViewById(R.id.name);
            ButtonReg = findViewById(R.id.ButtonAktif);
            Button_Reg = findViewById(R.id.Button_Nonaktif);
            button = findViewById(R.id.button1);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (username.getText().toString().length() == 0) {
                        username.setError("The Username field is required");
                    } else if (password.getText().toString().length() == 0) {
                        password.setError("The Password field is required");
                    } else if (email.getText().toString().length() == 0) {
                        email.setError("The Email field is required");
                    } else {
                        List<String>roles = new ArrayList<>();
                        roles.add(listItem.getSelectedItem().toString());
                        newregister(username.getText().toString(), password.getText().toString(), email.getText().toString(), name.getText().toString(),roles, rbaktif);
                    }

                }
            });
        }
        private void newregister(String username, String password, String email, String name, List<String> roles, boolean active) {
            SignUpBody signUpBody = new SignUpBody(username, password, email, name,roles, active);
            UserApiService apiService = retrofit.create(UserApiService.class);
            Call<SignUpResult> result = apiService.SigupUser(signUpBody);

            result.enqueue(new Callback<SignUpResult>() {
                @Override
                public void onResponse(Call<SignUpResult> call, Response<SignUpResult> response) {
                    try {
                        if (response.body().isSuccess()) {
                            Log.e("TAG", "Login Success" + response.body().toString());
                            Toast.makeText(Registration.this,  "Signup Berhasil :" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            Log.e("TAG", "login gagal" + response.body().toString());
                            Toast.makeText(Registration.this,  "Signup Gagal :" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SignUpResult> call, Throwable t) {

                    t.printStackTrace();
                }
            });

        }
    }


