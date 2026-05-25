package com.example.gpcustomizingicons

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Amber Lawson
// GP Customizing Icons
// 05/21/2026
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add the icon to the Action Bar
        val actionBar = supportActionBar
        actionBar!!.setIcon(R.mipmap.ic_launcher)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        //get our button and add an event handler
        val btnConvert = findViewById<Button>(R.id.btnConvert)
        btnConvert.setOnClickListener {
            val txtWeight = findViewById<EditText>(R.id.txtWeight)
            val enteredWt = txtWeight.text.toString().toDouble()
            val btnLbToKilo = findViewById<RadioButton>(R.id.radLbToKilo)
            val txtResult = findViewById<TextView>(R.id.txtResult)

            if (btnLbToKilo.isChecked) {
                //Pounds to Kilograms is checked, convert entered lbs to kg
                //Only convert if entered weight is 500 or less
                if (enteredWt <= 500) {
                    //conversion formula is kilos = pounds / 2.2
                    val weightInKilos = enteredWt / 2.2
                    //display the result, formatted as 1 decimal place
                    val txtWt = String.format("%.1f", weightInKilos)
                    txtResult.text = "$txtWt kg"
                } else {
                    //Show the user a message that the pounds value is too high
                    Toast.makeText(this, "Pounds must be 500 or less.",
                        Toast.LENGTH_LONG).show()
                }
            } else {
                //Kilo to pounds must be checked since it's a radio group
                //Only convert if entered weight is 225 or less
                if (enteredWt <= 225) {
                    //conversion formula is pounds = kilos * 2.2
                    val weightInLbs = enteredWt * 2.2
                    //display the result, formatted as 1 decimal place
                    val txtWt = String.format("%.1f", weightInLbs)
                    txtResult.text = "$txtWt lbs"
                } else {
                    //Show the user a message that the kilos value is too high
                    Toast.makeText(this, "Kilos must be 225 or less.",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
