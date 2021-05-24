package com.example.capstonemobile.ui.disease

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstonemobile.databinding.ActivityDiseaseCheckBinding

class DiseaseCheckActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDiseaseCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiseaseCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}