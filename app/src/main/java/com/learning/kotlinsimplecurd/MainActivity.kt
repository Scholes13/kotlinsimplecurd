package com.learning.kotlinsimplecurd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var btnInsertData: Button
    private lateinit var btnFetchData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnInsertData = findViewById(R.id.btnInsertData)

        btnInsertData.setOnClickListener {
            val intent = Intent(this, insertactivity::class.java)
            startActivity(intent)
        }

    }
}