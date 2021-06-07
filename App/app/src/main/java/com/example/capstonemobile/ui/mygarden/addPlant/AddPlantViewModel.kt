package com.example.capstonemobile.ui.mygarden.addPlant

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonemobile.data.source.Repository
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import okhttp3.MultipartBody

class AddPlantViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel() {

    fun uploadImage(picture: MultipartBody.Part) = repository.uploadImage(picture).asLiveData()

    fun insertPlant(id: String, plant: PlantDetail) = repository.insertNewPlant(id,plant).asLiveData()

    fun getAllPlant() = repository.getPlant().asLiveData()
}