package com.example.capstonemobile.ui.mygarden.diseaseHistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.capstonemobile.databinding.ActivityDiseaseHistoryBinding
import com.example.capstonemobile.ui.mygarden.PlantDetailActivity
import com.example.capstonemobile.ui.mygarden.diseaseHistoryDetail.DetailDiseaseHistoryActivity
import com.example.capstonemobile.utils.SessionManagement
import com.ojanbelajar.moviekatalogue.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.toast

@AndroidEntryPoint
class DiseaseHistoryActivity : AppCompatActivity() {

    private lateinit var session: SessionManagement
    private lateinit var binding: ActivityDiseaseHistoryBinding
    private lateinit var adapter: DiseaseHistoryAdapter
    private val model: DiseaseHistoryViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        session = SessionManagement(this)
        adapter = DiseaseHistoryAdapter(this)
        binding = ActivityDiseaseHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        back()

        val plantId : String = intent.getStringExtra("plant").toString()
//        getAllDiseaseHistory(plantId)

        getAllDisease()
    }

    private fun getAllDisease() {
        val adapter = DiseaseHistoryAdapter(this)
        model.diseases.observe(this, Observer { diseases ->
            if (diseases != null) {
                when(diseases) {
                    is Resource.Success -> {
                        Log.d("nendra",diseases.data.toString())
                        Log.d("ini userid", session.user["id"].toString())
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
        with(binding.rvDisease) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter
        }
        adapter.onItemClick = { data ->
            startActivity<DetailDiseaseHistoryActivity>("disease" to data)
        }
    }

//    private fun getAllDiseaseHistory(plantId: String) {
//        val adapter = DiseaseHistoryAdapter(this)
//        binding.progressBar.visibility = View.VISIBLE
//        model.getAllUserPlantDiseases(session.user["id"].toString(), plantId).observe(this, Observer { diseases ->
//            Log.d("diseases",diseases.data.toString())
//            Log.d("ini userid", session.user["id"].toString())
//            Log.d("ini plantid", plantId)
//            if (diseases != null) {
//                when(diseases) {
//
//                    is Resource.Success -> {
//                        Log.d("nendra",diseases.data.toString())
//                        Log.d("ini userid", session.user["id"].toString())
//                        Log.d("ini plantid", plantId)
//                        if ( diseases.data != null) {
//                            binding.progressBar.visibility = View.GONE
//                            adapter.setData(diseases.data)
//                        }
//                    }
//                    is Resource.Loading -> {
//                        toast("Loading")
//                    }
//                    is Resource.Error -> {
//                        Log.d("ojan error disease",diseases.message.toString())
//                        toast("Error")
//                    }
//                }
//            }
//        })
//    }

    private fun back() {
        val button = binding.btnBack
        button.setOnClickListener {
            finish()
        }
    }
}