package com.example.capstonemobile.ui.mygarden

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.capstonemobile.data.source.Repository

class PlantDetailViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel() {

    fun getPlantDetail(id: String) = repository.getPlantById(id)
}