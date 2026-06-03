package com.example.door2doordonations;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ServicesNearMeActivity extends AppCompatActivity {

    // Screen title
    private TextView tvServicesTitle;

    // Search + map
    private EditText etSearchRecipients;
    private TextView tvMapPlaceholder;

    // Service cards
    private LinearLayout cardServiceHospital;
    private LinearLayout cardServiceDaycare;
    private LinearLayout cardServiceSalvationArmy;
    private LinearLayout cardServiceFamilyShelter;

    // Service icons
    private ImageView imgServiceHospital;
    private ImageView imgServiceDaycare;
    private ImageView imgServiceSalvationArmy;
    private ImageView imgServiceFamilyShelter;

    // Service labels
    private TextView tvServiceHospital;
    private TextView tvServiceDaycare;
    private TextView tvServiceSalvationArmy;
    private TextView tvServiceFamilyShelter;

    // Bottom nav
    private ImageButton navSettings;
    private ImageButton navServices;
    private ImageButton navDonate;
    private ImageButton navHistory;
    private ImageButton navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_near_me);

        // Bind views
        tvServicesTitle           = findViewById(R.id.tvServicesTitle);
        etSearchRecipients        = findViewById(R.id.etSearchRecipients);
        tvMapPlaceholder          = findViewById(R.id.tvMapPlaceholder);

        cardServiceHospital       = findViewById(R.id.cardServiceHospital);
        cardServiceDaycare        = findViewById(R.id.cardServiceDaycare);
        cardServiceSalvationArmy  = findViewById(R.id.cardServiceSalvationArmy);
        cardServiceFamilyShelter  = findViewById(R.id.cardServiceFamilyShelter);

        imgServiceHospital        = findViewById(R.id.imgServiceHospital);
        imgServiceDaycare         = findViewById(R.id.imgServiceDaycare);
        imgServiceSalvationArmy   = findViewById(R.id.imgServiceSalvationArmy);
        imgServiceFamilyShelter   = findViewById(R.id.imgServiceFamilyShelter);

        tvServiceHospital         = findViewById(R.id.tvServiceHospital);
        tvServiceDaycare          = findViewById(R.id.tvServiceDaycare);
        tvServiceSalvationArmy    = findViewById(R.id.tvServiceSalvationArmy);
        tvServiceFamilyShelter    = findViewById(R.id.tvServiceFamilyShelter);

        // Bottom nav
        navSettings = findViewById(R.id.navSettings);
        navServices = findViewById(R.id.navServices);
        navDonate   = findViewById(R.id.navDonate);
        navHistory  = findViewById(R.id.navHistory);
        navProfile  = findViewById(R.id.navProfile);

        navSettings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        navServices.setOnClickListener(v -> {
            if (!(this instanceof ServicesNearMeActivity)) {
                startActivity(new Intent(this, ServicesNearMeActivity.class));
            }
        });
        navDonate.setOnClickListener(v -> startActivity(new Intent(this, DonateActivity.class)));
        navHistory.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));
        navProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
    }
}
