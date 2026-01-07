package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val btn = findViewById<Button>(R.id.btnGoToLevels)
        btn.setOnClickListener {
            jumpToLevels()
        }
    }
    fun jumpToLevels() {
        val intent = Intent(this, LevelsActivity::class.java)
        startActivity(intent)
    }
}