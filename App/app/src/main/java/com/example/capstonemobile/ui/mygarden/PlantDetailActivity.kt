package com.example.capstonemobile.ui.mygarden

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.databinding.ActivityPlantDetailBinding
import com.ojanbelajar.moviekatalogue.utils.Status
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

class PlantDetailActivity: AppCompatActivity(){

    private lateinit var binding: ActivityPlantDetailBinding
    private val model: PlantDetailViewModel by viewModels()

    companion object {
        const val EXTRA_PLANT = "extra_plant"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val plant: PlantDetail? = intent.getParcelableExtra("plant")

        getPlantDetail(plant?.plantId ?: "")
        dailyCheckUp()
        disease()
        back()
    }

    private fun getPlantDetail(id: String){
        model.getPlantDetail(id).observe(this, Observer { detail ->
            if (detail != null){
                when(detail.status){
                    Status.SUCCESS -> {
                        Log.d("ojan",detail.data.toString())
                    }
                    Status.ERROR -> {
                        toast("Something Wrong")
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

    private fun disease() {
        val button = binding.cvDisease
        button.setOnClickListener {
            val intent = Intent(this, DiseaseHistoryActivity::class.java)
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