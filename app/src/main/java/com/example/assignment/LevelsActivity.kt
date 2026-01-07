package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LevelsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.levels)
        val btnLevel1 = findViewById<Button>(R.id.button1)
        btnLevel1.setOnClickListener {
            val intent = Intent(this, Level1Activity::class.java)
            startActivity(intent)
        }
        val btnLevel2 = findViewById<Button>(R.id.button2)
        btnLevel2.setOnClickListener {

            val intent = Intent(this, Level2Activity::class.java)
            startActivity(intent)
        }
    }
}