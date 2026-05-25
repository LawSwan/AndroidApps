package com.example.contactsmobileapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        findViewById<TextView>(R.id.txtDetailName).text = intent.getStringExtra("name") ?: ""
        findViewById<TextView>(R.id.txtDetailPhone).text = intent.getStringExtra("phone") ?: ""
        findViewById<TextView>(R.id.txtDetailEmail).text = intent.getStringExtra("email") ?: ""
        findViewById<TextView>(R.id.txtDetailAddress).text = intent.getStringExtra("address") ?: ""
    }
}
