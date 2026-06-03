package com.example.door2doordonations;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    // UI elements
    private ImageView imgLogo;
    private TextView tvAppName;
    private TextView tvTagline;
    private ProgressBar progressBarSplash;

    // Splash display duration in milliseconds
    private static final int SPLASH_DELAY = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Bind views
        imgLogo = findViewById(R.id.imgLogo);
        tvAppName = findViewById(R.id.tvAppName);
        tvTagline = findViewById(R.id.tvTagline);
        progressBarSplash = findViewById(R.id.progressBarSplash);

        // Navigate to MainActivity after delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, DonateActivity.class);
                startActivity(intent);
                finish(); // Remove splash from back stack
            }
        }, SPLASH_DELAY);
    }
}
