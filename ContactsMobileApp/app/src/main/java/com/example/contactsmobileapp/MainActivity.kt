package com.example.contactsmobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Amber Lawson
// PA - Contacts Mobile App
// 05/14/2026
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnContact1).setOnClickListener {
            startActivity(Intent(this, ContactDetailActivity::class.java).apply {
                putExtra("name", getString(R.string.contact1_name))
                putExtra("phone", getString(R.string.contact1_phone))
                putExtra("email", getString(R.string.contact1_email))
                putExtra("address", getString(R.string.contact1_address))
            })
        }

        findViewById<Button>(R.id.btnContact2).setOnClickListener {
            startActivity(Intent(this, ContactDetailActivity::class.java).apply {
                putExtra("name", getString(R.string.contact2_name))
                putExtra("phone", getString(R.string.contact2_phone))
                putExtra("email", getString(R.string.contact2_email))
                putExtra("address", getString(R.string.contact2_address))
            })
        }
    }
}
