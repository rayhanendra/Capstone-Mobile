package com.example.capstonemobile.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstonemobile.databinding.ActivityWhatToPlantDetailBinding

class WhatToPlantDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityWhatToPlantDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWhatToPlantDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}