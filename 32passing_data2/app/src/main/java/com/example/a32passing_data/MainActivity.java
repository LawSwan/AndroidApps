package com.example.a32passing_data;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

// Amber Lawson - GP Passing Data Between Activities - 2026-05-29
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add the icon to the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setIcon(R.drawable.ic_launcher_foreground);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        // get the text box and send message button
        EditText tb = findViewById(R.id.txtEditMessage);
        Button btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> {
            // get the text from the edit box
            String msg = tb.getText().toString();

            // create an intent to send the message to the message activity
            Intent i = new Intent(this, MessageActivity.class);
            i.putExtra("message", msg);
            startActivity(i);
        });
    }
}
