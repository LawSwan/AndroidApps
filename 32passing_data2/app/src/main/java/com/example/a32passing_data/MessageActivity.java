package com.example.a32passing_data;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

// Amber Lawson - GP Passing Data Between Activities - 2026-05-29
public class MessageActivity extends AppCompatActivity {

    TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // add the icon to the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setIcon(R.drawable.ic_launcher_foreground);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        // get the text view to update with the message received
        txtMessage = findViewById(R.id.txtMessage);

        // set the text view's text to the message received
        String received = null;
        if (getIntent().getExtras() != null) {
            received = getIntent().getExtras().getString("message");
        }
        txtMessage.setText(received != null ? received : "No message found");
    }
}
