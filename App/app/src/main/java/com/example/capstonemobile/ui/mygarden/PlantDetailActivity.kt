package com.example.capstonemobile.ui.mygarden

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.databinding.ActivityPlantDetailBinding
import com.example.capstonemobile.ui.mygarden.checkup.DailyCheckupActivity
import com.example.capstonemobile.ui.mygarden.diseaseHistory.DiseaseHistoryActivity
import com.ojanbelajar.moviekatalogue.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

@AndroidEntryPoint
class PlantDetailActivity: AppCompatActivity(){

    private lateinit var binding: ActivityPlantDetailBinding
    private val model: PlantDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val plant: PlantDetail? = intent.getParcelableExtra("plant")

        Log.d("plantid detail", plant.toString())

        getPlantDetail(plant?.plantId ?: "")
        dailyCheckUp()
        disease(plant?.plantId ?: "")
        back()
    }

    private fun getPlantDetail(id: String){
        model.getPlantDetail(id).observe(this, Observer { detail ->
            if (detail != null){
                when(detail){
                    is Resource.Success -> {
                        Log.d("ojan",detail.data.toString())

                    }
                    is Resource.Loading -> {
                        toast("Loading")
                    }
                    is Resource.Error -> {
                        toast("Error")
                    }

                }
            }
        })

    }


    private fun dailyCheckUp() {
        val button = binding.cvDailyCheckup
        button.setOnClickListener {
            val intent = Intent(this, DailyCheckupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun disease(id: String) {
        Log.d("pencet disease id", id)
        val button = binding.cvDisease
        button.setOnClickListener {
            startActivity<DiseaseHistoryActivity>("plant" to id)
        }
    }

    private fun back() {
        val button = binding.btnBack
        button.setOnClickListener {
            finish()
        }
    }
}