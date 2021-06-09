package com.example.capstonemobile.ui.mygarden.checkup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.capstonemobile.data.source.local.entity.NPK
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.databinding.ActivityDetailDailyCheckupBinding
import com.example.capstonemobile.ui.mygarden.PlantDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.startActivity

@AndroidEntryPoint
class DetailDailyCheckupActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetailDailyCheckupBinding
    private val model: CheckupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDailyCheckupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val npk: NPK = intent.getParcelableExtra("npk")!!
        setData(npk)
        back()
        save(npk)
        dontSave()
    }

    @SuppressLint("SetTextI18n")
    private fun setData(npk: NPK) {
        binding.progressHumidityBar.progress = npk.humidity.toInt() * 100
        binding.progressHumidity.text = "${npk.humidity.toInt() * 100 }%"
        binding.progressTemperatureBar.progress = npk.temperature.toInt() * 100
        binding.progressTemperature.text = "${npk.temperature.toInt() * 100 }%"
        binding.progressNitrogenBar.progress = npk.nitrogen.toInt() * 100
        binding.progressNitrogen.text = "${npk.nitrogen.toInt() * 100 }%"
        binding.progressPotassiumBar.progress = npk.potassium.toInt() * 100
        binding.progressPotassium.text = "${npk.potassium.toInt() * 100 }%"
        binding.progressPhosphorusBar.progress = npk.phosporus.toInt() * 100
        binding.progressPhosphorus.text = "${npk.phosporus.toInt() * 100 }%"
    }

    private fun dontSave(){
        binding.btnDontSave.setOnClickListener {
            finish()
        }
    }

    private fun save(npk: NPK){
        binding.btnSave.setOnClickListener {
            model.saveNpk(npk)
            finish()
        }
    }

    private fun back() {
        val button = binding.btnBack
        button.setOnClickListener {
            finish()
        }
    }
}