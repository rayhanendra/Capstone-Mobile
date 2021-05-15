package com.example.capstonemobile.ui.mygarden

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstonemobile.databinding.ActivityDetailDailyCheckupBinding

class DetailDailyCheckupActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetailDailyCheckupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDailyCheckupBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}