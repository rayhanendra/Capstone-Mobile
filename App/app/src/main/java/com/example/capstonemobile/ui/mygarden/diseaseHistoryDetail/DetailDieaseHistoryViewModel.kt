package com.example.capstonemobile.ui.mygarden.diseaseHistoryDetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonemobile.data.source.Repository

class DetailDieaseHistoryViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel(){

    val diseases = repository.getDisease().asLiveData()

//    fun getDiseaseById(idUser: String, idPlant: String, id: String) = repository.getDiseaseById(idUser, idPlant, id).asLiveData()
}