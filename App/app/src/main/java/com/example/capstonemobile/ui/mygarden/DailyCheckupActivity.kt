package com.example.capstonemobile.ui.mygarden

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstonemobile.databinding.ActivityDailyCheckupBinding

class DailyCheckupActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDailyCheckupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyCheckupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addDailyCheckup()
        back()
    }

    private fun addDailyCheckup() {
        val button = binding.btnAddDailyCheckup
        button.setOnClickListener {
            val intent = Intent(this, AddDailyCheckupActivity::class.java )
            startActivity(intent)
        }
    }

    private fun back() {
        val button = binding.btnBack
        button.setOnClickListener {
            finish()
        }
    }
}