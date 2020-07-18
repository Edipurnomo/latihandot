package com.example.splashlogin;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BookActivity extends AppCompatActivity {
    private BottomNavigationView btmNavigation;
    private String TAG = "bookactivity";
    private TextView textJudul;
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




    public void openHomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        HomeFragment strCode = new HomeFragment();
        fragmentTransaction.replace(R.id.content, strCode, "HomeFragment");
        fragmentTransaction.commit();
    }


    public void tambahFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        TambahFragment strCode = new TambahFragment();
        fragmentTransaction.replace(R.id.content, strCode, "TambahFragment");
        fragmentTransaction.commit();
    }


    public void openViewFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DialogFragment bookView = new DialogFragment();
        fragmentTransaction.replace(R.id.content, bookView, "book fragment");
        fragmentTransaction.commit();
    }

}