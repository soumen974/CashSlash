package com.example.cashslash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    ImageView Logo;
    TextView LogoName, LogoDesc;

    Animation topAni, midAni, bottomAni;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Logo = findViewById(R.id.logoimg);
        LogoName = findViewById(R.id.textlogo1);
        LogoDesc = findViewById(R.id.textlogo2);

        topAni = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        midAni = AnimationUtils.loadAnimation(this,R.anim.mid_animation);
        bottomAni = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        LogoName.setAnimation(topAni);
        Logo.setAnimation(midAni);
        LogoDesc.setAnimation(bottomAni);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, Landing_page.class);
                startActivity(intent);
                finish();
            }
        }, 4000);


    }
}
