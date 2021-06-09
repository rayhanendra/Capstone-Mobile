package com.example.capstonemobile.ui.disease

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.capstonemobile.databinding.ActivityDiseaseCheckBinding
import com.example.capstonemobile.ui.mygarden.diseaseHistory.DiseaseHistoryActivity
import com.example.capstonemobile.ui.mygarden.diseaseHistory.DiseaseHistoryAdapter
import com.example.capstonemobile.ui.mygarden.diseaseHistoryDetail.DetailDiseaseHistoryActivity
import com.ojanbelajar.moviekatalogue.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

@AndroidEntryPoint
class DiseaseCheckActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDiseaseCheckBinding
    private val model: DiseaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiseaseCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*
        getAllDisease()
*/

    }
    /*   private fun getAllDisease() {
        val adapter = DiseaseHistoryAdapter(this)
        model.diseases.observe(this, Observer { diseases ->
            if (diseases != null) {
                when(diseases) {
                    is Resource.Success -> {
                        if ( diseases.data != null) {
                            binding.progressBar.visibility = View.GONE
                            adapter.setData(diseases.data)
                        }
                    }
                    is Resource.Loading -> {
                        toast("Loading")
                    }
                    is Resource.Error -> {
                        Log.d("ojan error disease",diseases.message.toString())
                        toast("Error")
                    }
                }
            }
        })
        with(binding.rvDiseaseCheck) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter
        }
        adapter.onItemClick = { data ->
            startActivity<DetailDiseaseHistoryActivity>("disease" to data)
        }
    }*/

}