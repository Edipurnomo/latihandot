package com.example.splashlogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BookActivity extends AppCompatActivity {
 EditText judulbuku, penulis, penerbit, tahun, harga;
    Button home;
    Button plus;
    Button addimage;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        judulbuku = findViewById(R.id.judul);
        penulis = findViewById(R.id.penulis);
        penerbit = findViewById(R.id.penerbit);
        tahun = findViewById(R.id.tahun);
        harga = findViewById(R.id.harga);
        home = findViewById(R.id.home);
        plus = findViewById(R.id.plus);
        addimage = findViewById(R.id.addimage);
        send = findViewById(R.id.send);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahFragment();

            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeFragment();
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