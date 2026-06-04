package com.example.door2doordonations;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private TextView tvSettingsTitle;

    private LinearLayout rowUsernamePass;
    private LinearLayout rowAccountPayment;
    private LinearLayout rowLocationServices;
    private LinearLayout rowNotifications;
    private LinearLayout rowHelpSupport;

    private TextView tvUsernamePass;
    private TextView tvAccountPayment;
    private TextView tvLocationServices;
    private TextView tvNotifications;
    private TextView tvHelpSupport;

    private ImageView imgArrowUsernamePass;
    private ImageView imgArrowAccountPayment;
    private ImageView imgArrowHelpSupport;
    private Switch switchLocationServices;
    private Switch switchNotifications;

    private Button btnLogOut;

    private ImageButton navSettings;
    private ImageButton navServices;
    private ImageButton navDonate;
    private ImageButton navHistory;
    private ImageButton navProfile;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefs = getSharedPreferences(DonationStore.PREFS, MODE_PRIVATE);

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

        navSettings = findViewById(R.id.navSettings);
        navServices = findViewById(R.id.navServices);
        navDonate   = findViewById(R.id.navDonate);
        navHistory  = findViewById(R.id.navHistory);
        navProfile  = findViewById(R.id.navProfile);

        wireSwitches();
        wireRows();
        wireLogOut();
        wireBottomNav();
    }

    private void wireSwitches() {
        switchLocationServices.setChecked(prefs.getBoolean(DonationStore.KEY_LOCATION, true));
        switchNotifications.setChecked(prefs.getBoolean(DonationStore.KEY_NOTIFICATIONS, true));

        switchLocationServices.setOnCheckedChangeListener((b, isChecked) -> {
            prefs.edit().putBoolean(DonationStore.KEY_LOCATION, isChecked).apply();
            Toast.makeText(this, "Location services " + (isChecked ? "on" : "off"),
                    Toast.LENGTH_SHORT).show();
        });
        switchNotifications.setOnCheckedChangeListener((b, isChecked) -> {
            prefs.edit().putBoolean(DonationStore.KEY_NOTIFICATIONS, isChecked).apply();
            Toast.makeText(this, "Notifications " + (isChecked ? "on" : "off"),
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void wireRows() {
        rowUsernamePass.setOnClickListener(v ->
                showInfoDialog("Username & Password",
                        "Change your username or reset your password here."));
        rowAccountPayment.setOnClickListener(v ->
                showInfoDialog("Account / Payment",
                        "Manage your saved payment method for the $20 pickup fee."));
        rowHelpSupport.setOnClickListener(v ->
                showInfoDialog("Help & Support",
                        "Email support@door2doordonations.example or call 1-555-DONATE."));

        rowLocationServices.setOnClickListener(v ->
                switchLocationServices.toggle());
        rowNotifications.setOnClickListener(v ->
                switchNotifications.toggle());
    }

    private void wireLogOut() {
        btnLogOut.setOnClickListener(v ->
                new AlertDialog.Builder(this)
                        .setTitle("Log out?")
                        .setMessage("This will clear your donation history on this device.")
                        .setPositiveButton("Log out", (d, w) -> {
                            new DonationStore(this).clear();
                            prefs.edit().clear().apply();
                            Intent i = new Intent(this, SplashActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                            finish();
                        })
                        .setNegativeButton("Cancel", null)
                        .show());
    }

    private void showInfoDialog(String title, String body) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(body)
                .setPositiveButton("OK", null)
                .show();
    }

    private void wireBottomNav() {
        navSettings.setOnClickListener(v -> { /* already here */ });
        navServices.setOnClickListener(v -> startActivity(new Intent(this, ServicesNearMeActivity.class)));
        navDonate.setOnClickListener(v -> startActivity(new Intent(this, DonateActivity.class)));
        navHistory.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));
        navProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
    }
}
