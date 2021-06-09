package com.example.capstonemobile.ui.mygarden.diseaseHistoryDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.capstonemobile.data.source.local.entity.DiseaseDetailEntity
import com.example.capstonemobile.data.source.local.entity.DiseaseEntity
import com.example.capstonemobile.databinding.ActivityDetailDiseaseHistoryBinding
import com.example.capstonemobile.utils.SessionManagement
import com.ojanbelajar.moviekatalogue.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast

@AndroidEntryPoint
class DetailDiseaseHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDiseaseHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDiseaseHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val diseaseEntity: DiseaseEntity = intent.getParcelableExtra("disease")!!
        populateDiseaseDetail(diseaseEntity)
        back()
    }



    private fun populateDiseaseDetail(diseaseEntity: DiseaseEntity) {
        binding.tvDiseaseName.text = diseaseEntity.diseasesName
        binding.tvDiseaseDescription.text= diseaseEntity.diseasesDetail
        binding.treatmentTitle.text = diseaseEntity.diseasesTreatment
        binding.videoTitle.text = diseaseEntity.diseaseTreatmentVid
    }

    private fun back() {
        val button = binding.btnBack
        button.setOnClickListener {
            finish()
        }
    }
}