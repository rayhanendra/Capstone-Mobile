package com.example.capstonemobile.ui.mygarden

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstonemobile.databinding.ActivityDailyCheckupBinding

class DailyCheckupActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDailyCheckupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyCheckupBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}