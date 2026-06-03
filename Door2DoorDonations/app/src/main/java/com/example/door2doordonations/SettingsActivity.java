package com.example.door2doordonations;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    // Screen title
    private TextView tvSettingsTitle;

    // Setting rows
    private LinearLayout rowUsernamePass;
    private LinearLayout rowAccountPayment;
    private LinearLayout rowLocationServices;
    private LinearLayout rowNotifications;
    private LinearLayout rowHelpSupport;

    // Row labels
    private TextView tvUsernamePass;
    private TextView tvAccountPayment;
    private TextView tvLocationServices;
    private TextView tvNotifications;
    private TextView tvHelpSupport;

    // Row arrows / controls
    private ImageView imgArrowUsernamePass;
    private ImageView imgArrowAccountPayment;
    private ImageView imgArrowHelpSupport;
    private Switch switchLocationServices;
    private Switch switchNotifications;

    // Log out
    private Button btnLogOut;

    // Bottom nav
    private ImageButton navSettings;
    private ImageButton navServices;
    private ImageButton navDonate;
    private ImageButton navHistory;
    private ImageButton navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Bind views
        tvSettingsTitle      = findViewById(R.id.tvSettingsTitle);

        rowUsernamePass      = findViewById(R.id.rowUsernamePass);
        rowAccountPayment    = findViewById(R.id.rowAccountPayment);
        rowLocationServices  = findViewById(R.id.rowLocationServices);
        rowNotifications     = findViewById(R.id.rowNotifications);
        rowHelpSupport       = findViewById(R.id.rowHelpSupport);

        tvUsernamePass       = findViewById(R.id.tvUsernamePass);
        tvAccountPayment     = findViewById(R.id.tvAccountPayment);
        tvLocationServices   = findViewById(R.id.tvLocationServices);
        tvNotifications      = findViewById(R.id.tvNotifications);
        tvHelpSupport        = findViewById(R.id.tvHelpSupport);

        imgArrowUsernamePass    = findViewById(R.id.imgArrowUsernamePass);
        imgArrowAccountPayment  = findViewById(R.id.imgArrowAccountPayment);
        imgArrowHelpSupport     = findViewById(R.id.imgArrowHelpSupport);
        switchLocationServices  = findViewById(R.id.switchLocationServices);
        switchNotifications     = findViewById(R.id.switchNotifications);

        btnLogOut = findViewById(R.id.btnLogOut);

        // Bottom nav
        navSettings = findViewById(R.id.navSettings);
        navServices = findViewById(R.id.navServices);
        navDonate   = findViewById(R.id.navDonate);
        navHistory  = findViewById(R.id.navHistory);
        navProfile  = findViewById(R.id.navProfile);

        navSettings.setOnClickListener(v -> {
            if (!(this instanceof SettingsActivity)) {
                startActivity(new Intent(this, SettingsActivity.class));
            }
        });
        navServices.setOnClickListener(v -> startActivity(new Intent(this, ServicesNearMeActivity.class)));
        navDonate.setOnClickListener(v -> startActivity(new Intent(this, DonateActivity.class)));
        navHistory.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));
        navProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
    }
}
