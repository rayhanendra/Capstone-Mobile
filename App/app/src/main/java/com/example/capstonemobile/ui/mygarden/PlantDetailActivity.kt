package com.example.capstonemobile.ui.mygarden

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstonemobile.databinding.ActivityPlantDetailBinding

class PlantDetailActivity: AppCompatActivity(){

    private lateinit var binding: ActivityPlantDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dailyCheckUp()
        disease()
        back()
    }

    private fun dailyCheckUp() {
        val button = binding.cvDailyCheckup
        button.setOnClickListener {
            val intent = Intent(this, DailyCheckupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun disease() {
        val button = binding.cvDisease
        button.setOnClickListener {
            val intent = Intent(this, DiseaseHistoryActivity::class.java)
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