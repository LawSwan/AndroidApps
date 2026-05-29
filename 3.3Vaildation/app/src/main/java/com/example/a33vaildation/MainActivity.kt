// Author: Amber Lawson
// Assignment: GP Input Validation & Data Storage
// Date: 2026-05-29

package com.example.a33vaildation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add the icon to the Action Bar
        supportActionBar?.apply {
            setIcon(R.drawable.ic_launcher_foreground)
            setDisplayUseLogoEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        //get the UI controls with the values
        val years = findViewById<Spinner>(R.id.selYears)
        val loan = findViewById<EditText>(R.id.numLoanAmt)
        val interest = findViewById<EditText>(R.id.numInterest)

        //get the button and the shared preferences file
        val btnPayment = findViewById<Button>(R.id.btnPayment)
        val sharedPref = getSharedPreferences("User_Values", MODE_PRIVATE)

        btnPayment.setOnClickListener {
            val yearSel = years.selectedItem.toString()
            val intLoan = loan.text.toString().toIntOrNull()
            val decInterest = interest.text.toString().toFloatOrNull()

            if (intLoan == null || decInterest == null) {
                Toast.makeText(
                    this,
                    "Please enter a valid loan amount and interest rate",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            //validate the interest value is between 0 and .3 (30%)
            if ((decInterest < 0.0f) || (decInterest > .30f)) {
                //invalid interest value, tell the user
                Toast.makeText(
                    this,
                    "Interest rate must be between 0.0 and 0.3",
                    Toast.LENGTH_LONG,
                ).show()
            } else {
                //store the values in shared preferences
                val editor = sharedPref.edit()
                editor.putString("selected_year", yearSel)
                editor.putInt("loan_amount", intLoan)
                editor.putFloat("interest_rate", decInterest)
                editor.apply()
                //start the payment activity
                startActivity(Intent(this, PaymentActivity::class.java))
            }
        }
    }
}
