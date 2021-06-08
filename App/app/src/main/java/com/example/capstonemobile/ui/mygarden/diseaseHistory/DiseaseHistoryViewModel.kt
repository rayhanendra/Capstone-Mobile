package com.example.capstonemobile.ui.mygarden.diseaseHistory

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonemobile.data.source.Repository

class DiseaseHistoryViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel(){

    val diseases = repository.getDisease().asLiveData()

    fun getAllUserPlantDiseases(idUser: String, idPlant: String) = repository.getDiseaseByUserId(idUser, idPlant).asLiveData()
}