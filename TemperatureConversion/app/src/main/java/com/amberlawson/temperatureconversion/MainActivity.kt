// Name:   Amber Lawson
// Date:   May 23, 2026
// Course: Mobile Application Development
// Description: Temperature Conversion App — converts between Fahrenheit and Celsius
//              with enforced input bounds and toast validation messages.

package com.amberlawson.temperatureconversion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val FAHRENHEIT_MIN = -100.0
    private val FAHRENHEIT_MAX =  250.0
    private val CELSIUS_MIN    =  -75.0
    private val CELSIUS_MAX    =  125.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etTemperature: EditText   = findViewById(R.id.etTemperature)
        val rgConversion:  RadioGroup = findViewById(R.id.rgConversion)
        val rbFtoC:        RadioButton = findViewById(R.id.rbFtoC)
        val btnConvert:    Button     = findViewById(R.id.btnConvert)
        val tvResult:      TextView   = findViewById(R.id.tvResult)

        btnConvert.setOnClickListener {
            val inputStr = etTemperature.text.toString().trim()

            if (inputStr.isEmpty()) {
                Toast.makeText(this, "Please enter a temperature value.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val inputValue = inputStr.toDoubleOrNull()
            if (inputValue == null) {
                Toast.makeText(this, "Please enter a valid numeric value.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (rbFtoC.isChecked) {
                if (inputValue < FAHRENHEIT_MIN || inputValue > FAHRENHEIT_MAX) {
                    Toast.makeText(
                        this,
                        "°F value must be between ${FAHRENHEIT_MIN.toInt()}°F and ${FAHRENHEIT_MAX.toInt()}°F.",
                        Toast.LENGTH_LONG
                    ).show()
                    tvResult.text = getString(R.string.result_placeholder)
                    return@setOnClickListener
                }
                val celsius = (inputValue - 32.0) * 5.0 / 9.0
                tvResult.text = "%.1f °F  =  %.1f °C".format(inputValue, celsius)
            } else {
                if (inputValue < CELSIUS_MIN || inputValue > CELSIUS_MAX) {
                    Toast.makeText(
                        this,
                        "°C value must be between ${CELSIUS_MIN.toInt()}°C and ${CELSIUS_MAX.toInt()}°C.",
                        Toast.LENGTH_LONG
                    ).show()
                    tvResult.text = getString(R.string.result_placeholder)
                    return@setOnClickListener
                }
                val fahrenheit = (inputValue * 9.0 / 5.0) + 32.0
                tvResult.text = "%.1f °C  =  %.1f °F".format(inputValue, fahrenheit)
            }
        }
    }
}
