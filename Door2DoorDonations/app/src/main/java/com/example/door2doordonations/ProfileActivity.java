package com.example.door2doordonations;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    // Avatar and identity
    private ImageView imgProfileAvatar;
    private TextView tvProfileUsername;
    private ImageButton btnEditProfile;

    // Stats
    private TextView tvDonationsCount;
    private TextView tvMemberSince;

    // Bio
    private TextView tvAboutMe;

    // Tags
    private TextView tagParent;
    private TextView tagStudent;
    private TextView tagMvp;

    // Bottom nav
    private ImageButton navSettings;
    private ImageButton navServices;
    private ImageButton navDonate;
    private ImageButton navHistory;
    private ImageButton navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Bind views
        imgProfileAvatar  = findViewById(R.id.imgProfileAvatar);
        tvProfileUsername = findViewById(R.id.tvProfileUsername);
        btnEditProfile    = findViewById(R.id.btnEditProfile);
        tvDonationsCount  = findViewById(R.id.tvDonationsCount);
        tvMemberSince     = findViewById(R.id.tvMemberSince);
        tvAboutMe         = findViewById(R.id.tvAboutMe);
        tagParent         = findViewById(R.id.tagParent);
        tagStudent        = findViewById(R.id.tagStudent);
        tagMvp            = findViewById(R.id.tagMvp);

        // Bottom nav
        navSettings = findViewById(R.id.navSettings);
        navServices = findViewById(R.id.navServices);
        navDonate   = findViewById(R.id.navDonate);
        navHistory  = findViewById(R.id.navHistory);
        navProfile  = findViewById(R.id.navProfile);

        navSettings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        navServices.setOnClickListener(v -> startActivity(new Intent(this, ServicesNearMeActivity.class)));
        navDonate.setOnClickListener(v -> startActivity(new Intent(this, DonateActivity.class)));
        navHistory.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));
        navProfile.setOnClickListener(v -> {
            if (!(this instanceof ProfileActivity)) {
                startActivity(new Intent(this, ProfileActivity.class));
            }
        });
    }
}
