package com.example.splashlogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BookActivity extends AppCompatActivity {

    Button home;
    Button plus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        home = findViewById(R.id.home);
        plus = findViewById(R.id.plus);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeFragment();
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahFragment();

            }
        });

        }
    private void openHomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        HomeFragment strCode = new HomeFragment();
        fragmentTransaction.replace(R.id.content, strCode, "HomeFragment");
        fragmentTransaction.commit();
    }


    private void tambahFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        TambahFragment strCode = new TambahFragment();
        fragmentTransaction.replace(R.id.content, strCode, "TambahFragment");
        fragmentTransaction.commit();
    }

}