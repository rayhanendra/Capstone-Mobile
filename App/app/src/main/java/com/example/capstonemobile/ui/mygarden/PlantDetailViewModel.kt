package com.example.capstonemobile.ui.mygarden

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonemobile.data.source.Repository
import okhttp3.RequestBody

class PlantDetailViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel() {

    fun getPlantDetail(id: String) = repository.getPlantById(id).asLiveData()

    fun getNpk(body: RequestBody) = repository.getNPK(body).asLiveData()
}