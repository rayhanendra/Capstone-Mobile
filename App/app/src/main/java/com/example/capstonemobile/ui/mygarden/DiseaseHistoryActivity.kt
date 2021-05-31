package com.example.capstonemobile.ui.mygarden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstonemobile.R
import com.example.capstonemobile.databinding.ActivityDiseaseHistoryBinding

class DiseaseHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiseaseHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiseaseHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        back()
    }

    private fun back() {
        val button = binding.btnBack
        button.setOnClickListener {
            finish()
        }
    }
}