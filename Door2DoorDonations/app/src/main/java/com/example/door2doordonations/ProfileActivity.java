package com.example.door2doordonations;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private ImageView imgProfileAvatar;
    private TextView tvProfileUsername;
    private ImageButton btnEditProfile;

    private TextView tvDonationsCount;
    private TextView tvMemberSince;

    private TextView tvAboutMe;

    private TextView tagParent;
    private TextView tagStudent;
    private TextView tagMvp;

    private ImageButton navSettings;
    private ImageButton navServices;
    private ImageButton navDonate;
    private ImageButton navHistory;
    private ImageButton navProfile;

    private final boolean[] tagSelected = new boolean[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgProfileAvatar  = findViewById(R.id.imgProfileAvatar);
        tvProfileUsername = findViewById(R.id.tvProfileUsername);
        btnEditProfile    = findViewById(R.id.btnEditProfile);
        tvDonationsCount  = findViewById(R.id.tvDonationsCount);
        tvMemberSince     = findViewById(R.id.tvMemberSince);
        tvAboutMe         = findViewById(R.id.tvAboutMe);
        tagParent         = findViewById(R.id.tagParent);
        tagStudent        = findViewById(R.id.tagStudent);
        tagMvp            = findViewById(R.id.tagMvp);

        navSettings = findViewById(R.id.navSettings);
        navServices = findViewById(R.id.navServices);
        navDonate   = findViewById(R.id.navDonate);
        navHistory  = findViewById(R.id.navHistory);
        navProfile  = findViewById(R.id.navProfile);

        btnEditProfile.setOnClickListener(v -> showEditDialog());

        tagSelected[2] = true; // MVP starts highlighted per existing layout
        tagParent.setOnClickListener(v -> toggleTag(tagParent, 0));
        tagStudent.setOnClickListener(v -> toggleTag(tagStudent, 1));
        tagMvp.setOnClickListener(v -> toggleTag(tagMvp, 2));

        wireBottomNav();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int count = new DonationStore(this).getCount();
        tvDonationsCount.setText(String.valueOf(count));
    }

    private void toggleTag(TextView tag, int index) {
        tagSelected[index] = !tagSelected[index];
        if (tagSelected[index]) {
            tag.setBackgroundResource(R.drawable.tag_filled);
            tag.setTextColor(0xFFFFFFFF);
        } else {
            tag.setBackgroundResource(R.drawable.tag_border);
            tag.setTextColor(0xFFE87722);
        }
    }

    private void showEditDialog() {
        final android.widget.EditText input = new android.widget.EditText(this);
        input.setText(tvAboutMe.getText());

        new AlertDialog.Builder(this)
                .setTitle("Edit About Me")
                .setView(input)
                .setPositiveButton("Save", (d, w) -> {
                    tvAboutMe.setText(input.getText().toString());
                    Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void wireBottomNav() {
        navSettings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        navServices.setOnClickListener(v -> startActivity(new Intent(this, ServicesNearMeActivity.class)));
        navDonate.setOnClickListener(v -> startActivity(new Intent(this, DonateActivity.class)));
        navHistory.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));
        navProfile.setOnClickListener(v -> { /* already here */ });
    }
}
