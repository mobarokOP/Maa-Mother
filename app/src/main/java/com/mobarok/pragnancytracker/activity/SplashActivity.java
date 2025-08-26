package com.mobarok.pragnancytracker.activity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobarok.pragnancytracker.R;
import com.mobarok.pragnancytracker.tools.CustomPref;

public class SplashActivity extends AppCompatActivity {

    CustomPref customPref;
    ImageView logoIV;
    TextView titleTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        EdgeToEdge.enable(this);
        logoIV = findViewById(R.id.logoIV);
        titleTV = findViewById(R.id.titleTV);
        customPref = new CustomPref(this);
        Animation animLogo = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        Animation animTitle = AnimationUtils.loadAnimation(this, R.anim.slide_in_from_bottom);
        logoIV.startAnimation(animLogo);
        titleTV.startAnimation(animTitle);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (customPref.getSession()){
                    startActivity(new Intent(SplashActivity.this, IntroActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }
        },3000);

    }
}