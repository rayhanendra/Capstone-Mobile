package com.example.capstonemobile.ui.mygarden

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstonemobile.databinding.ActivityAddPlantBinding


class AddPlantActivity: AppCompatActivity(){

    private lateinit var binding: ActivityAddPlantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPlantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addPlant()
        back()
    }

    private fun addPlant() {
        val addButton = binding.btnAddPlant
        addButton.setOnClickListener {
            val intent = Intent(this, PlantDetailActivity::class.java)
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