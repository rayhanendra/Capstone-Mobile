package com.example.capstonemobile.ui.mygarden.diseaseHistoryDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.capstonemobile.data.source.local.entity.DiseaseDetailEntity
import com.example.capstonemobile.databinding.ActivityDetailDiseaseHistoryBinding
import com.example.capstonemobile.utils.SessionManagement
import com.ojanbelajar.moviekatalogue.utils.Resource
import org.jetbrains.anko.toast

class DetailDiseaseHistoryActivity : AppCompatActivity() {

    private lateinit var session: SessionManagement
    private lateinit var binding: ActivityDetailDiseaseHistoryBinding
    private val model: DetailDieaseHistoryViewModel by viewModels()

    companion object {
        const val EXTRA_DISEASE = "extra_disease"
        const val EXTRA_PLANT = "extra_plant"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDiseaseHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val diseaseEntity: DiseaseDetailEntity? = intent.getParcelableExtra("disease")

        val idPlant: String = intent.getStringExtra("extra_plant").toString()
        val id: String = intent.getStringExtra("extra_disease").toString()
        getDiseaseDetail(idPlant, id)
        back()
    }

    private fun getDiseaseDetail(idPlant: String, id: String ) {
        model.getDiseaseById(session.user["id"].toString(), idPlant, id).observe(this, Observer { disease ->
            if (disease !=null) {
                when(disease) {
                    is Resource.Success -> {
                        Log.d("ojan",disease.data.toString())
                        if ( disease.data != null) {
                                populateDiseaseDetail(disease)
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

    private fun populateDiseaseDetail(diseaseDetailEntity: Resource<DiseaseDetailEntity>) {
        binding.tvDiseaseName.text = diseaseDetailEntity.data?.id
        binding.tvDiseaseDescription.text= diseaseDetailEntity.data?.userPlantId
    }

    private fun back() {
        val button = binding.btnBack
        button.setOnClickListener {
            finish()
        }
    }
}