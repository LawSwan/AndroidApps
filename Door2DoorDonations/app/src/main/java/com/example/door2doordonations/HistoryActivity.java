package com.example.door2doordonations;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

    // Screen title
    private TextView tvHistoryTitle;

    // History row cards
    private LinearLayout cardHistoryHospital;
    private LinearLayout cardHistoryDaycare;
    private LinearLayout cardHistorySalvationArmy;
    private LinearLayout cardHistoryFamilyShelter;

    // Row labels
    private TextView tvHistoryHospitalName;
    private TextView tvHistoryHospitalStatus;
    private TextView tvHistoryHospitalAmount;

    private TextView tvHistoryDaycareName;
    private TextView tvHistoryDaycareStatus;
    private TextView tvHistoryDaycareAmount;

    private TextView tvHistorySalvationArmyName;
    private TextView tvHistorySalvationArmyStatus;
    private TextView tvHistorySalvationArmyAmount;

    private TextView tvHistoryFamilyShelterName;
    private TextView tvHistoryFamilyShelterStatus;
    private TextView tvHistoryFamilyShelterAmount;

    // Bottom nav
    private ImageButton navSettings;
    private ImageButton navServices;
    private ImageButton navDonate;
    private ImageButton navHistory;
    private ImageButton navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Bind views
        tvHistoryTitle = findViewById(R.id.tvHistoryTitle);

        cardHistoryHospital       = findViewById(R.id.cardHistoryHospital);
        cardHistoryDaycare        = findViewById(R.id.cardHistoryDaycare);
        cardHistorySalvationArmy  = findViewById(R.id.cardHistorySalvationArmy);
        cardHistoryFamilyShelter  = findViewById(R.id.cardHistoryFamilyShelter);

        tvHistoryHospitalName     = findViewById(R.id.tvHistoryHospitalName);
        tvHistoryHospitalStatus   = findViewById(R.id.tvHistoryHospitalStatus);
        tvHistoryHospitalAmount   = findViewById(R.id.tvHistoryHospitalAmount);

        tvHistoryDaycareName      = findViewById(R.id.tvHistoryDaycareName);
        tvHistoryDaycareStatus    = findViewById(R.id.tvHistoryDaycareStatus);
        tvHistoryDaycareAmount    = findViewById(R.id.tvHistoryDaycareAmount);

        tvHistorySalvationArmyName   = findViewById(R.id.tvHistorySalvationArmyName);
        tvHistorySalvationArmyStatus = findViewById(R.id.tvHistorySalvationArmyStatus);
        tvHistorySalvationArmyAmount = findViewById(R.id.tvHistorySalvationArmyAmount);

        tvHistoryFamilyShelterName   = findViewById(R.id.tvHistoryFamilyShelterName);
        tvHistoryFamilyShelterStatus = findViewById(R.id.tvHistoryFamilyShelterStatus);
        tvHistoryFamilyShelterAmount = findViewById(R.id.tvHistoryFamilyShelterAmount);

        // Bottom nav
        navSettings = findViewById(R.id.navSettings);
        navServices = findViewById(R.id.navServices);
        navDonate   = findViewById(R.id.navDonate);
        navHistory  = findViewById(R.id.navHistory);
        navProfile  = findViewById(R.id.navProfile);

        navSettings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        navServices.setOnClickListener(v -> startActivity(new Intent(this, ServicesNearMeActivity.class)));
        navDonate.setOnClickListener(v -> startActivity(new Intent(this, DonateActivity.class)));
        navHistory.setOnClickListener(v -> {
            if (!(this instanceof HistoryActivity)) {
                startActivity(new Intent(this, HistoryActivity.class));
            }
        });
        navProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
    }
}
