package com.example.door2doordonations;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ServicesNearMeActivity extends AppCompatActivity {

    private TextView tvServicesTitle;

    private EditText etSearchRecipients;
    private TextView tvMapPlaceholder;

    private LinearLayout cardServiceHospital;
    private LinearLayout cardServiceDaycare;
    private LinearLayout cardServiceSalvationArmy;
    private LinearLayout cardServiceFamilyShelter;

    private ImageView imgServiceHospital;
    private ImageView imgServiceDaycare;
    private ImageView imgServiceSalvationArmy;
    private ImageView imgServiceFamilyShelter;

    private TextView tvServiceHospital;
    private TextView tvServiceDaycare;
    private TextView tvServiceSalvationArmy;
    private TextView tvServiceFamilyShelter;

    private ImageButton navSettings;
    private ImageButton navServices;
    private ImageButton navDonate;
    private ImageButton navHistory;
    private ImageButton navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_near_me);

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

        navSettings = findViewById(R.id.navSettings);
        navServices = findViewById(R.id.navServices);
        navDonate   = findViewById(R.id.navDonate);
        navHistory  = findViewById(R.id.navHistory);
        navProfile  = findViewById(R.id.navProfile);

        wireCardClicks();
        wireSearch();
        wireBottomNav();
    }

    private void wireCardClicks() {
        cardServiceHospital.setOnClickListener(v ->
                openDonate(tvServiceHospital.getText().toString()));
        cardServiceDaycare.setOnClickListener(v ->
                openDonate(tvServiceDaycare.getText().toString()));
        cardServiceSalvationArmy.setOnClickListener(v ->
                openDonate(tvServiceSalvationArmy.getText().toString()));
        cardServiceFamilyShelter.setOnClickListener(v ->
                openDonate(tvServiceFamilyShelter.getText().toString()));
    }

    private void openDonate(String fullLabel) {
        // strip "— 1.2 mi" trailing distance for a cleaner recipient name
        String recipient = fullLabel;
        int dash = fullLabel.indexOf('—');
        if (dash > 0) recipient = fullLabel.substring(0, dash).trim();

        Intent intent = new Intent(this, DonateActivity.class);
        intent.putExtra(DonateActivity.EXTRA_RECIPIENT, recipient);
        startActivity(intent);
    }

    private void wireSearch() {
        etSearchRecipients.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                String q = s.toString().trim().toLowerCase();
                applyFilter(cardServiceHospital, tvServiceHospital.getText().toString(), q);
                applyFilter(cardServiceDaycare, tvServiceDaycare.getText().toString(), q);
                applyFilter(cardServiceSalvationArmy, tvServiceSalvationArmy.getText().toString(), q);
                applyFilter(cardServiceFamilyShelter, tvServiceFamilyShelter.getText().toString(), q);
            }
        });
    }

    private void applyFilter(View card, String label, String query) {
        if (query.isEmpty() || label.toLowerCase().contains(query)) {
            card.setVisibility(View.VISIBLE);
        } else {
            card.setVisibility(View.GONE);
        }
    }

    private void wireBottomNav() {
        navSettings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        navServices.setOnClickListener(v -> { /* already here */ });
        navDonate.setOnClickListener(v -> startActivity(new Intent(this, DonateActivity.class)));
        navHistory.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));
        navProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
    }
}
