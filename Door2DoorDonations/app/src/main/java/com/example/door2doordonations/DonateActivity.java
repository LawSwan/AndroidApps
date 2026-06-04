package com.example.door2doordonations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DonateActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPIENT = "extra_recipient";

    private TextView tvDonateTitle;
    private TextView tvHistoryHeader;

    private LinearLayout cardHospital;
    private LinearLayout cardDayCare;
    private LinearLayout cardSalvationArmy;

    private TextView tvHospitalName;
    private TextView tvDayCareName;
    private TextView tvSalvationArmyName;

    private Button btnDonateHospital;
    private Button btnDonateDayCare;
    private Button btnDonateSalvationArmy;

    private SeekBar seekBarDonate;
    private TextView tvSlideLabel;

    private Button btnDonate;

    private ImageButton navSettings;
    private ImageButton navServices;
    private ImageButton navDonate;
    private ImageButton navHistory;
    private ImageButton navProfile;

    private String selectedRecipient = null;
    private boolean slideConfirmed = false;
    private DonationStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        store = new DonationStore(this);

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

        navSettings = findViewById(R.id.navSettings);
        navServices = findViewById(R.id.navServices);
        navDonate   = findViewById(R.id.navDonate);
        navHistory  = findViewById(R.id.navHistory);
        navProfile  = findViewById(R.id.navProfile);

        wireRecipientSelection();
        wireSeekBar();
        wireDonateButton();
        wireBottomNav();

        String incoming = getIntent().getStringExtra(EXTRA_RECIPIENT);
        if (incoming != null) {
            selectRecipient(incoming);
        } else {
            btnDonate.setEnabled(false);
            btnDonate.setAlpha(0.5f);
        }
    }

    private void wireRecipientSelection() {
        View.OnClickListener hospital = v -> selectRecipient(tvHospitalName.getText().toString());
        cardHospital.setOnClickListener(hospital);
        btnDonateHospital.setOnClickListener(hospital);

        View.OnClickListener daycare = v -> selectRecipient(tvDayCareName.getText().toString());
        cardDayCare.setOnClickListener(daycare);
        btnDonateDayCare.setOnClickListener(daycare);

        View.OnClickListener salvation = v -> selectRecipient(tvSalvationArmyName.getText().toString());
        cardSalvationArmy.setOnClickListener(salvation);
        btnDonateSalvationArmy.setOnClickListener(salvation);
    }

    private void selectRecipient(String name) {
        selectedRecipient = name;
        slideConfirmed = false;
        seekBarDonate.setProgress(0);
        tvSlideLabel.setText("Selected: " + name + " — slide to confirm");
        btnDonate.setEnabled(false);
        btnDonate.setAlpha(0.5f);
        Toast.makeText(this, name + " selected", Toast.LENGTH_SHORT).show();
    }

    private void wireSeekBar() {
        seekBarDonate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar bar, int progress, boolean fromUser) {
                if (selectedRecipient == null) {
                    if (fromUser) bar.setProgress(0);
                    return;
                }
                if (progress >= 95) {
                    slideConfirmed = true;
                    btnDonate.setEnabled(true);
                    btnDonate.setAlpha(1f);
                    tvSlideLabel.setText("Confirmed — tap DONATE to finish");
                } else {
                    slideConfirmed = false;
                    btnDonate.setEnabled(false);
                    btnDonate.setAlpha(0.5f);
                    tvSlideLabel.setText("Selected: " + selectedRecipient + " — slide to confirm");
                }
            }

            @Override public void onStartTrackingTouch(SeekBar bar) { }

            @Override
            public void onStopTrackingTouch(SeekBar bar) {
                if (!slideConfirmed) bar.setProgress(0);
            }
        });
    }

    private void wireDonateButton() {
        btnDonate.setOnClickListener(v -> {
            if (selectedRecipient == null) {
                Toast.makeText(this, "Pick a recipient first", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!slideConfirmed) {
                Toast.makeText(this, "Slide to confirm first", Toast.LENGTH_SHORT).show();
                return;
            }
            store.addDonation(selectedRecipient);
            Toast.makeText(this,
                    "$20 donation scheduled to " + selectedRecipient,
                    Toast.LENGTH_LONG).show();

            selectedRecipient = null;
            slideConfirmed = false;
            seekBarDonate.setProgress(0);
            tvSlideLabel.setText(R.string.label_slide_to_donate);
            btnDonate.setEnabled(false);
            btnDonate.setAlpha(0.5f);

            startActivity(new Intent(this, HistoryActivity.class));
        });
    }

    private void wireBottomNav() {
        navSettings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        navServices.setOnClickListener(v -> startActivity(new Intent(this, ServicesNearMeActivity.class)));
        navDonate.setOnClickListener(v -> { /* already here */ });
        navHistory.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));
        navProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
    }
}
