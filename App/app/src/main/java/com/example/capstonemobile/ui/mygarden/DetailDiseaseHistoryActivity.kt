package com.example.capstonemobile.ui.mygarden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstonemobile.R
import com.example.capstonemobile.databinding.ActivityDetailDiseaseHistoryBinding

class DetailDiseaseHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDiseaseHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_disease_history)

        back()
    }

    private fun back() {
        val button = binding.btnBack
        button.setOnClickListener {
            finish()
        }
    }
}