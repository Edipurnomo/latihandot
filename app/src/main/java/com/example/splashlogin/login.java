package com.example.splashlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class login extends AppCompatActivity {
    private Button buttonLogin;
    private TextView Register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.buttonLogin);
        Register = findViewById(R.id.Register);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                // action
                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                // action
                Intent intent = new Intent(login.this, NewReg.class);
                startActivity(intent);
            }
        });



    }
}