package com.example.personalphotogalleryapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView largeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Setup Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.app_name));
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.drawable.flower_icon);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        largeImage = findViewById(R.id.largeImage);

        setupThumbnail(R.id.img1);
        setupThumbnail(R.id.img2);
        setupThumbnail(R.id.img3);
        setupThumbnail(R.id.img4);
        setupThumbnail(R.id.img5);
        setupThumbnail(R.id.img6);
    }

    private void setupThumbnail(int id) {
        findViewById(id).setOnClickListener(v -> {
            ImageView iv = (ImageView) v;
            largeImage.setImageDrawable(iv.getDrawable());
        });
    }
}