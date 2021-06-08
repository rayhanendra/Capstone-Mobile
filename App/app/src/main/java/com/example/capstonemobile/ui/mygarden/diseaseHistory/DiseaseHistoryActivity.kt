package com.example.capstonemobile.ui.mygarden.diseaseHistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.capstonemobile.R
import com.example.capstonemobile.databinding.ActivityDiseaseHistoryBinding
import com.example.capstonemobile.utils.SessionManagement
import com.ojanbelajar.moviekatalogue.utils.Resource
import org.jetbrains.anko.toast

class DiseaseHistoryActivity : AppCompatActivity() {

    private lateinit var session: SessionManagement
    private lateinit var binding: ActivityDiseaseHistoryBinding
    private val model: DiseaseHistoryViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiseaseHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        back()

        val plantId: String = intent.getStringExtra("EXTRA_PLANT").toString()
        getAllDiseaseHistory(plantId)
    }

    private fun getAllDiseaseHistory(plantId: String) {
        val adapter = DiseaseHistoryAdapter(this)
        binding.progressBar.visibility = View.VISIBLE
        model.getAllUserPlantDiseases(session.user["id"].toString(), plantId).observe(this, Observer { diseases ->
            if (diseases != null) {
                when(diseases) {
                    is Resource.Success -> {
                        Log.d("ojan",diseases.data.toString())
                        if ( diseases.data != null) {
                            binding.progressBar.visibility = View.GONE
                            adapter.setData(diseases.data)
                        }
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

    private fun back() {
        val button = binding.btnBack
        button.setOnClickListener {
            finish()
        }
    }
}