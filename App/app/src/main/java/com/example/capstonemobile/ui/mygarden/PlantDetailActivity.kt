package com.example.capstonemobile.ui.mygarden

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.capstonemobile.data.source.local.entity.NPK
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.databinding.ActivityPlantDetailBinding
import com.example.capstonemobile.ui.mygarden.checkup.AddDailyCheckupActivity
import com.example.capstonemobile.ui.mygarden.diseaseHistory.DiseaseHistoryActivity
import com.ojanbelajar.moviekatalogue.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.roundToLong

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class PlantDetailActivity: AppCompatActivity(){

    private lateinit var binding: ActivityPlantDetailBinding
    private val model: PlantDetailViewModel by viewModels()
    private var isNpk = false
    private var npk: List<NPK> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val plant: PlantDetail = intent.getParcelableExtra("plant")!!

        Log.d("plantid detail", plant.toString())

        getPlantDetail(plant.plantId,plant)
        dailyCheckUp(plant.plantId)
        disease(plant.plantId)
        getNpk()
        back()
    }

    private fun getNpk(){
        if (model.getNpk() != null){
         npk = model.getNpk()
         isNpk = true
        }
    }

    private fun getPlantDetail(id: String,plantDetail: PlantDetail){
        model.getPlantDetail(id).observe(this, Observer { detail ->
            if (detail != null){
                when(detail){
                    is Resource.Success -> {
                        setData(detail.data!!,plantDetail)
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


    private fun dailyCheckUp(id: String) {
        val button = binding.cvDailyCheckup
        button.setOnClickListener {
           startActivity<AddDailyCheckupActivity>("plant" to id )
        }
    }

    private fun disease(id: String) {
        Log.d("pencet disease id", id)
        val button = binding.cvDisease
        button.setOnClickListener {
            startActivity<DiseaseHistoryActivity>("plant" to id)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setData(plant: Plant, plantDetail: PlantDetail){
        binding.namePlant.text = plantDetail.plantName
        binding.plantName.text = plantDetail.plantName
        binding.plantNameLatin.text = "${plant.plantClass}/${plant.plantSpecies}"
        binding.plantDescription.text = plantDetail.plantDetail
        val formatter = DateTimeFormatter.ofPattern("EEEE,dd MMM yyyy")
        val instant = Instant.ofEpochMilli((plantDetail.createdAt).roundToLong())
        val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).plusYears(52)
        binding.datePlant.text = formatter.format(date)
        binding.textPhase.text = plantDetail.plantPhase
        if (!isNpk){
            binding.progressHumidityBar.progress = 0
            binding.progressHumidity.text = "${0}$"
            binding.progressTemperatureBar.progress = 0
            binding.progressTemperature.text = "${0}$"
            binding.progressNitrogenBar.progress = 0
            binding.progressNitrogen.text = "${0}$"
            binding.progressPotassiumBar.progress = 0
            binding.progressPotassium.text = "${0}$"
            binding.progressPhosphorusBar.progress = 0
            binding.progressPhosphorus.text = "${0}$"
        } else {
            binding.progressHumidityBar.progress = npk[0].humidity.toInt() * 100
            binding.progressHumidity.text = "${npk[0].humidity.toInt() * 100 }%"
            binding.progressTemperatureBar.progress = npk[0].temperature.toInt() * 100
            binding.progressTemperature.text = "${npk[0].temperature.toInt() * 100 }%"
            binding.progressNitrogenBar.progress = npk[0].nitrogen.toInt() * 100
            binding.progressNitrogen.text = "${npk[0].nitrogen.toInt() * 100 }%"
            binding.progressPotassiumBar.progress = npk[0].potassium.toInt() * 100
            binding.progressPotassium.text = "${npk[0].potassium.toInt() * 100 }%"
            binding.progressPhosphorusBar.progress = npk[0].phosporus.toInt() * 100
            binding.progressPhosphorus.text = "${npk[0].phosporus.toInt() * 100 }%"
        }

    }

    private fun back() {
        val button = binding.btnBack
        button.setOnClickListener {
            finish()
        }
    }
}