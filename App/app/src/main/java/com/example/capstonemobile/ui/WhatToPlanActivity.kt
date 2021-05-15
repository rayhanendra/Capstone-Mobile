package com.example.capstonemobile.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstonemobile.databinding.ActivityWhatToPlanBinding

class WhatToPlanActivity: AppCompatActivity() {

    private lateinit var binding: ActivityWhatToPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWhatToPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}