package com.example.capstonemobile.ui.mygarden

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstonemobile.databinding.ActivityAddDailyCheckupBinding

class AddDailyCheckupActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddDailyCheckupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDailyCheckupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        save()
        back()
    }

    private fun save() {
        val button = binding.btnSave
        button.setOnClickListener {
            val intent = Intent(this, DetailDailyCheckupActivity::class.java)
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