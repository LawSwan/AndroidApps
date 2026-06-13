package com.example.door2doordonations;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

public class HistoryActivity extends AppCompatActivity {

    private TextView tvHistoryTitle;
    private LinearLayout historyListContainer;
    private TextView tvHistoryEmpty;

    private ImageButton navSettings;
    private ImageButton navServices;
    private ImageButton navDonate;
    private ImageButton navHistory;
    private ImageButton navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tvHistoryTitle       = findViewById(R.id.tvHistoryTitle);
        historyListContainer = findViewById(R.id.historyListContainer);
        tvHistoryEmpty       = findViewById(R.id.tvHistoryEmpty);

        navSettings = findViewById(R.id.navSettings);
        navServices = findViewById(R.id.navServices);
        navDonate   = findViewById(R.id.navDonate);
        navHistory  = findViewById(R.id.navHistory);
        navProfile  = findViewById(R.id.navProfile);

        wireBottomNav();
    }

    @Override
    protected void onResume() {
        super.onResume();
        renderHistory();
    }

    private void renderHistory() {
        List<DonationStore.Donation> donations = new DonationStore(this).getAll();

        // Clear existing cards but keep the empty-state TextView (at index 0)
        int childCount = historyListContainer.getChildCount();
        if (childCount > 1) {
            historyListContainer.removeViews(1, childCount - 1);
        }

        if (donations.isEmpty()) {
            tvHistoryTitle.setText(R.string.title_history_screen);
            tvHistoryEmpty.setVisibility(View.VISIBLE);
            return;
        }

        tvHistoryEmpty.setVisibility(View.GONE);
        tvHistoryTitle.setText(R.string.title_history_screen);

        LayoutInflater inflater = LayoutInflater.from(this);
        for (DonationStore.Donation d : donations) {
            View card = inflater.inflate(R.layout.item_history_card, historyListContainer, false);

            TextView name     = card.findViewById(R.id.itemHistoryName);
            TextView category = card.findViewById(R.id.itemHistoryCategory);
            TextView pickup   = card.findViewById(R.id.itemHistoryPickup);
            TextView status   = card.findViewById(R.id.itemHistoryStatus);
            TextView amount   = card.findViewById(R.id.itemHistoryAmount);

            name.setText(d.recipient + " · " + d.date);

            if (d.category != null && !d.category.isEmpty()) {
                category.setText(d.category.toUpperCase(Locale.US));
                category.setVisibility(View.VISIBLE);
            } else {
                category.setVisibility(View.GONE);
            }

            if (d.pickupTime != null && !d.pickupTime.isEmpty()) {
                pickup.setText("Pickup: " + d.pickupTime);
                pickup.setVisibility(View.VISIBLE);
            } else {
                pickup.setVisibility(View.GONE);
            }

            status.setText(d.status);
            amount.setText(String.format(Locale.US, "$%.2f", d.amount));

            historyListContainer.addView(card);
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
