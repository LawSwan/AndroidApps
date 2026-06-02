package com.example.tickethub_lawson;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerEvents;
    EditText etTickets;
    Button btnCalculate;
    TextView tvResult;

    double[] prices = {49.99, 45.99, 42.99};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerEvents = findViewById(R.id.spinnerEvents);
        etTickets = findViewById(R.id.etTickets);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.events_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEvents.setAdapter(adapter);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedIndex = spinnerEvents.getSelectedItemPosition();
                String ticketsStr = etTickets.getText().toString();

                if (ticketsStr.isEmpty()) {
                    tvResult.setText("Please enter number of tickets.");
                    return;
                }

                int numTickets = Integer.parseInt(ticketsStr);
                double total = numTickets * prices[selectedIndex];
                tvResult.setText(String.format("Total Cost: $%.2f", total));
            }
        });
    }
}