package com.example.door2doordonations;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DonateActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPIENT = "extra_recipient";

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

    private TextView chipAmount10;
    private TextView chipAmount20;
    private TextView chipAmount50;
    private TextView chipAmountCustom;

    private TextView chipCatClothes;
    private TextView chipCatFood;
    private TextView chipCatToys;
    private TextView chipCatMoney;

    private TextView btnPickupTime;

    private SeekBar seekBarDonate;
    private TextView tvSlideLabel;

    private Button btnDonate;

    private ImageButton navSettings;
    private ImageButton navServices;
    private ImageButton navDonate;
    private ImageButton navHistory;
    private ImageButton navProfile;

    private LinearLayout[] recipientCards;
    private TextView[] amountChips;
    private TextView[] categoryChips;

    private String selectedRecipient = null;
    private double selectedAmount = 20.00;
    private String selectedCategory = null;
    private Calendar pickupCalendar = null;
    private boolean slideConfirmed = false;
    private DonationStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        store = new DonationStore(this);

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

        chipAmount10          = findViewById(R.id.chipAmount10);
        chipAmount20          = findViewById(R.id.chipAmount20);
        chipAmount50          = findViewById(R.id.chipAmount50);
        chipAmountCustom      = findViewById(R.id.chipAmountCustom);

        chipCatClothes        = findViewById(R.id.chipCatClothes);
        chipCatFood           = findViewById(R.id.chipCatFood);
        chipCatToys           = findViewById(R.id.chipCatToys);
        chipCatMoney          = findViewById(R.id.chipCatMoney);

        btnPickupTime         = findViewById(R.id.btnPickupTime);

        seekBarDonate         = findViewById(R.id.seekBarDonate);
        tvSlideLabel          = findViewById(R.id.tvSlideLabel);

        btnDonate             = findViewById(R.id.btnDonate);

        navSettings = findViewById(R.id.navSettings);
        navServices = findViewById(R.id.navServices);
        navDonate   = findViewById(R.id.navDonate);
        navHistory  = findViewById(R.id.navHistory);
        navProfile  = findViewById(R.id.navProfile);

        recipientCards = new LinearLayout[]{ cardHospital, cardDayCare, cardSalvationArmy };
        amountChips    = new TextView[]{ chipAmount10, chipAmount20, chipAmount50, chipAmountCustom };
        categoryChips  = new TextView[]{ chipCatClothes, chipCatFood, chipCatToys, chipCatMoney };

        wireRecipientSelection();
        wireAmountChips();
        wireCategoryChips();
        wirePickupTime();
        wireSeekBar();
        wireDonateButton();
        wireBottomNav();

        selectAmount(20.00, chipAmount20);

        String incoming = getIntent().getStringExtra(EXTRA_RECIPIENT);
        if (incoming != null) {
            selectRecipient(incoming);
        }
        updateDonateEnabled();
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
        for (LinearLayout card : recipientCards) {
            TextView label = (TextView) card.getChildAt(0);
            boolean isSelected = label.getText().toString().equals(name);
            card.setBackgroundResource(isSelected
                    ? R.drawable.chip_selected
                    : R.drawable.card_modern_bg);
            label.setTextColor(isSelected ? 0xFFFFFFFF : 0xFF222222);
        }
        resetSlide();
        updateDonateEnabled();
    }

    private void wireAmountChips() {
        chipAmount10.setOnClickListener(v -> selectAmount(10.00, chipAmount10));
        chipAmount20.setOnClickListener(v -> selectAmount(20.00, chipAmount20));
        chipAmount50.setOnClickListener(v -> selectAmount(50.00, chipAmount50));
        chipAmountCustom.setOnClickListener(v -> showCustomAmountDialog());
    }

    private void selectAmount(double amount, TextView chip) {
        selectedAmount = amount;
        for (TextView c : amountChips) {
            boolean isSelected = c == chip;
            c.setBackgroundResource(isSelected
                    ? R.drawable.chip_selected
                    : R.drawable.chip_unselected);
            c.setTextColor(isSelected ? 0xFFFFFFFF : 0xFF222222);
        }
        resetSlide();
    }

    private void showCustomAmountDialog() {
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        input.setHint(getString(R.string.custom_amount_hint));

        new AlertDialog.Builder(this)
                .setTitle(R.string.custom_amount_title)
                .setView(input)
                .setPositiveButton("Set", (d, w) -> {
                    String text = input.getText().toString().trim();
                    if (text.isEmpty()) return;
                    try {
                        double val = Double.parseDouble(text);
                        if (val <= 0) {
                            Toast.makeText(this, "Amount must be positive", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        selectedAmount = val;
                        chipAmountCustom.setText(String.format(Locale.US, "$%.0f", val));
                        for (TextView c : amountChips) {
                            boolean isSelected = c == chipAmountCustom;
                            c.setBackgroundResource(isSelected
                                    ? R.drawable.chip_selected
                                    : R.drawable.chip_unselected);
                            c.setTextColor(isSelected ? 0xFFFFFFFF : 0xFF222222);
                        }
                        resetSlide();
                    } catch (NumberFormatException ignored) {
                        Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void wireCategoryChips() {
        chipCatClothes.setOnClickListener(v -> selectCategory("Clothes", chipCatClothes));
        chipCatFood.setOnClickListener(v -> selectCategory("Food", chipCatFood));
        chipCatToys.setOnClickListener(v -> selectCategory("Toys", chipCatToys));
        chipCatMoney.setOnClickListener(v -> selectCategory("Money", chipCatMoney));
    }

    private void selectCategory(String name, TextView chip) {
        selectedCategory = name;
        for (TextView c : categoryChips) {
            boolean isSelected = c == chip;
            c.setBackgroundResource(isSelected
                    ? R.drawable.chip_selected
                    : R.drawable.chip_unselected);
            c.setTextColor(isSelected ? 0xFFFFFFFF : 0xFF222222);
        }
        resetSlide();
        updateDonateEnabled();
    }

    private void wirePickupTime() {
        btnPickupTime.setOnClickListener(v -> openDatePicker());
    }

    private void openDatePicker() {
        Calendar now = Calendar.getInstance();
        Calendar seed = pickupCalendar != null ? pickupCalendar : now;
        DatePickerDialog dlg = new DatePickerDialog(this, (view, year, month, day) -> {
            Calendar chosen = Calendar.getInstance();
            chosen.set(Calendar.YEAR, year);
            chosen.set(Calendar.MONTH, month);
            chosen.set(Calendar.DAY_OF_MONTH, day);
            openTimePicker(chosen);
        }, seed.get(Calendar.YEAR), seed.get(Calendar.MONTH), seed.get(Calendar.DAY_OF_MONTH));
        dlg.getDatePicker().setMinDate(now.getTimeInMillis() - 1000);
        dlg.show();
    }

    private void openTimePicker(Calendar dateOnly) {
        Calendar seed = pickupCalendar != null ? pickupCalendar : Calendar.getInstance();
        new TimePickerDialog(this, (view, hour, minute) -> {
            dateOnly.set(Calendar.HOUR_OF_DAY, hour);
            dateOnly.set(Calendar.MINUTE, minute);
            pickupCalendar = dateOnly;
            String label = new SimpleDateFormat("MMM d 'at' h:mm a", Locale.US).format(pickupCalendar.getTime());
            btnPickupTime.setText(label);
            btnPickupTime.setTextColor(0xFF222222);
            resetSlide();
            updateDonateEnabled();
        }, seed.get(Calendar.HOUR_OF_DAY), seed.get(Calendar.MINUTE), false).show();
    }

    private void wireSeekBar() {
        seekBarDonate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar bar, int progress, boolean fromUser) {
                if (!readyToSlide()) {
                    if (fromUser) bar.setProgress(0);
                    return;
                }
                if (progress >= 95) {
                    if (!slideConfirmed) {
                        slideConfirmed = true;
                        haptic();
                        tvSlideLabel.setText("✓ Confirmed — tap DONATE to finish");
                        tvSlideLabel.setTextColor(0xFFFFFFFF);
                    }
                    btnDonate.setEnabled(true);
                    btnDonate.setAlpha(1f);
                } else {
                    slideConfirmed = false;
                    btnDonate.setEnabled(false);
                    btnDonate.setAlpha(0.5f);
                    tvSlideLabel.setText("Slide all the way to confirm");
                    tvSlideLabel.setTextColor(0xF2FFFFFF);
                }
            }

            @Override public void onStartTrackingTouch(SeekBar bar) { }

            @Override
            public void onStopTrackingTouch(SeekBar bar) {
                if (!slideConfirmed) bar.setProgress(0);
            }
        });
    }

    private boolean readyToSlide() {
        return selectedRecipient != null && selectedCategory != null && pickupCalendar != null;
    }

    private void updateDonateEnabled() {
        boolean enabled = slideConfirmed && readyToSlide();
        btnDonate.setEnabled(enabled);
        btnDonate.setAlpha(enabled ? 1f : 0.5f);
        if (!readyToSlide()) {
            tvSlideLabel.setText(nextRequiredHint());
        }
    }

    private String nextRequiredHint() {
        if (selectedRecipient == null) return "Pick a recipient";
        if (selectedCategory == null)  return "Pick a category";
        if (pickupCalendar == null)    return "Schedule a pickup time";
        return getString(R.string.label_slide_to_donate);
    }

    private void resetSlide() {
        slideConfirmed = false;
        seekBarDonate.setProgress(0);
        btnDonate.setEnabled(false);
        btnDonate.setAlpha(0.5f);
        tvSlideLabel.setText(nextRequiredHint());
        tvSlideLabel.setTextColor(0xF2FFFFFF);
    }

    private void wireDonateButton() {
        btnDonate.setOnClickListener(v -> {
            if (!readyToSlide() || !slideConfirmed) {
                Toast.makeText(this, nextRequiredHint(), Toast.LENGTH_SHORT).show();
                return;
            }
            String pickupLabel = new SimpleDateFormat("MMM d 'at' h:mm a", Locale.US)
                    .format(pickupCalendar.getTime());
            store.addDonation(selectedRecipient, selectedAmount, selectedCategory, pickupLabel);
            Toast.makeText(this,
                    String.format(Locale.US, "$%.2f donation scheduled to %s",
                            selectedAmount, selectedRecipient),
                    Toast.LENGTH_LONG).show();
            haptic();
            startActivity(new Intent(this, HistoryActivity.class));
            finish();
        });
    }

    private void haptic() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            VibratorManager vm = (VibratorManager) getSystemService(Context.VIBRATOR_MANAGER_SERVICE);
            if (vm != null) {
                vm.getDefaultVibrator().vibrate(VibrationEffect.createOneShot(40, VibrationEffect.DEFAULT_AMPLITUDE));
            }
        } else {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (v != null && v.hasVibrator()) {
                v.vibrate(VibrationEffect.createOneShot(40, VibrationEffect.DEFAULT_AMPLITUDE));
            }
        }
    }

    private void wireBottomNav() {
        navSettings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        navServices.setOnClickListener(v -> startActivity(new Intent(this, ServicesNearMeActivity.class)));
        navDonate.setOnClickListener(v -> { /* already here */ });
        navHistory.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));
        navProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
    }
}
