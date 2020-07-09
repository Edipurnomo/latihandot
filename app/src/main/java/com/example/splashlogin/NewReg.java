package com.example.splashlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class NewReg extends AppCompatActivity {
    EditText username, email, password, nama;
    LauncherActivity.ListItem listItem;
    RadioButton ButtonReg, Button_Reg;
    Button button;

    String Username, Email, Password, Nama, Radio, Radio3, List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        nama = findViewById(R.id.fullname);
        ButtonReg = findViewById(R.id.ButtonReg);
        Button_Reg = findViewById(R.id.Button_Reg);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (username.getText().toString().length() == 0) {
                    username.setError("The Username field is required");
                } else if (password.getText().toString().length() == 0) {
                    password.setError("The Password field is required");
                } else if (email.getText().toString().length() == 0) {
                    email.setError("The Email field is required");
                } else if (nama.getText().toString().length() == 0) {
                    nama.setError("The Name field is required ");
                }
            }
        });
    }
}