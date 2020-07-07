package com.example.splashlogin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo= (ImageView) findViewById(R.id.logo);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent panggil = new Intent(SplashActivity.this, login.class);
                startActivity(panggil);
                finish();

            }
        }, 5000);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mysplashanimaation);
        logo.startAnimation(myanim);
    }
}
