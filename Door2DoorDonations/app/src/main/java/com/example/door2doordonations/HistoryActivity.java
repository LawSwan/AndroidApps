package com.example.door2doordonations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

public class HistoryActivity extends AppCompatActivity {

    private TextView tvHistoryTitle;

    private LinearLayout cardHistoryHospital;
    private LinearLayout cardHistoryDaycare;
    private LinearLayout cardHistorySalvationArmy;
    private LinearLayout cardHistoryFamilyShelter;

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

    private ImageButton navSettings;
    private ImageButton navServices;
    private ImageButton navDonate;
    private ImageButton navHistory;
    private ImageButton navProfile;

    private LinearLayout[] cards;
    private TextView[] names;
    private TextView[] statuses;
    private TextView[] amounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

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

        navSettings = findViewById(R.id.navSettings);
        navServices = findViewById(R.id.navServices);
        navDonate   = findViewById(R.id.navDonate);
        navHistory  = findViewById(R.id.navHistory);
        navProfile  = findViewById(R.id.navProfile);

        cards = new LinearLayout[]{
                cardHistoryHospital, cardHistoryDaycare,
                cardHistorySalvationArmy, cardHistoryFamilyShelter };
        names = new TextView[]{
                tvHistoryHospitalName, tvHistoryDaycareName,
                tvHistorySalvationArmyName, tvHistoryFamilyShelterName };
        statuses = new TextView[]{
                tvHistoryHospitalStatus, tvHistoryDaycareStatus,
                tvHistorySalvationArmyStatus, tvHistoryFamilyShelterStatus };
        amounts = new TextView[]{
                tvHistoryHospitalAmount, tvHistoryDaycareAmount,
                tvHistorySalvationArmyAmount, tvHistoryFamilyShelterAmount };

        wireBottomNav();
    }

    @Override
    protected void onResume() {
        super.onResume();
        renderHistory();
    }

    private void renderHistory() {
        List<DonationStore.Donation> donations = new DonationStore(this).getAll();
        int shown = Math.min(donations.size(), cards.length);

        for (int i = 0; i < shown; i++) {
            DonationStore.Donation d = donations.get(i);
            cards[i].setVisibility(View.VISIBLE);
            names[i].setText(d.recipient + " · " + d.date);
            statuses[i].setText(d.status);
            amounts[i].setText(String.format(Locale.US, "$%.2f", d.amount));
        }
        for (int i = shown; i < cards.length; i++) {
            cards[i].setVisibility(View.GONE);
        }

        if (donations.isEmpty()) {
            tvHistoryTitle.setText("Donation History — none yet");
        } else {
            tvHistoryTitle.setText(R.string.title_history_screen);
        }
    }

    private void wireBottomNav() {
        navSettings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        navServices.setOnClickListener(v -> startActivity(new Intent(this, ServicesNearMeActivity.class)));
        navDonate.setOnClickListener(v -> startActivity(new Intent(this, DonateActivity.class)));
        navHistory.setOnClickListener(v -> { /* already here */ });
        navProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
    }
}
