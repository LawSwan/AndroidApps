package com.example.door2doordonations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DonateActivity extends AppCompatActivity {

    // Screen title
    private TextView tvDonateTitle;

    // History of service label
    private TextView tvHistoryHeader;

    // Recipient cards
    private LinearLayout cardHospital;
    private LinearLayout cardDayCare;
    private LinearLayout cardSalvationArmy;

    // Recipient name labels
    private TextView tvHospitalName;
    private TextView tvDayCareName;
    private TextView tvSalvationArmyName;

    // Go buttons on each card
    private Button btnDonateHospital;
    private Button btnDonateDayCare;
    private Button btnDonateSalvationArmy;

    // Slide to donate
    private SeekBar seekBarDonate;
    private TextView tvSlideLabel;

    // Main donate button
    private Button btnDonate;

    // Bottom nav
    private ImageButton navSettings;
    private ImageButton navServices;
    private ImageButton navDonate;
    private ImageButton navHistory;
    private ImageButton navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        // Bind views
        tvDonateTitle         = findViewById(R.id.tvDonateTitle);
        tvHistoryHeader       = findViewById(R.id.tvHistoryHeader);

        cardHospital          = findViewById(R.id.cardHospital);
        cardDayCare           = findViewById(R.id.cardDayCare);
        cardSalvationArmy     = findViewById(R.id.cardSalvationArmy);

        tvHospitalName        = findViewById(R.id.tvHospitalName);
        tvDayCareName         = findViewById(R.id.tvDayCareName);
        tvSalvationArmyName   = findViewById(R.id.tvSalvationArmyName);

        btnDonateHospital     = findViewById(R.id.btnDonateHospital);
        btnDonateDayCare      = findViewById(R.id.btnDonateDayCare);
        btnDonateSalvationArmy = findViewById(R.id.btnDonateSalvationArmy);

        seekBarDonate         = findViewById(R.id.seekBarDonate);
        tvSlideLabel          = findViewById(R.id.tvSlideLabel);

        btnDonate             = findViewById(R.id.btnDonate);

        // Bottom nav
        navSettings = findViewById(R.id.navSettings);
        navServices = findViewById(R.id.navServices);
        navDonate   = findViewById(R.id.navDonate);
        navHistory  = findViewById(R.id.navHistory);
        navProfile  = findViewById(R.id.navProfile);

        navSettings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        navServices.setOnClickListener(v -> startActivity(new Intent(this, ServicesNearMeActivity.class)));
        navDonate.setOnClickListener(v -> {
            if (!(this instanceof DonateActivity)) {
                startActivity(new Intent(this, DonateActivity.class));
            }
        });
        navHistory.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));
        navProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
    }
}
