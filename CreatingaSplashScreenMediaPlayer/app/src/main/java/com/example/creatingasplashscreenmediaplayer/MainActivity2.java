package com.example.creatingasplashscreenmediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

// Amber Lawson - GP Splash Screen & MediaPlayer - 2026-06-04
public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // add the icon to the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setIcon(R.mipmap.ic_launcher);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        final boolean[] playing = {false};

        final Button btnUkulele = findViewById(R.id.btnUkulele);
        final MediaPlayer mpUkulele = MediaPlayer.create(this, R.raw.ukulele);
        final Button btnDrums = findViewById(R.id.btnDrums);
        final MediaPlayer mpDrums = MediaPlayer.create(this, R.raw.drums);

        btnDrums.setOnClickListener(v -> {
            if (playing[0]) {
                mpDrums.pause();
                playing[0] = false;
                btnDrums.setText("Play Drums Song");
                btnUkulele.setVisibility(View.VISIBLE);
            } else {
                mpDrums.start();
                playing[0] = true;
                btnDrums.setText("Pause Drums Song");
                btnUkulele.setVisibility(View.INVISIBLE);
            }
        });

        btnUkulele.setOnClickListener(v -> {
            if (playing[0]) {
                mpUkulele.pause();
                playing[0] = false;
                btnUkulele.setText("Play Ukulele Song");
                btnDrums.setVisibility(View.VISIBLE);
            } else {
                mpUkulele.start();
                playing[0] = true;
                btnUkulele.setText("Pause Ukulele Song");
                btnDrums.setVisibility(View.INVISIBLE);
            }
        });
    }
}
