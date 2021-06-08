package com.example.capstonemobile.ui.mygarden.diseaseHistory

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonemobile.data.source.Repository

class DiseaseHistoryViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel(){
    fun getAllUserPlantDiseases(idUser: String, idPlant: String ) = repository.getDiseaseByUser(idUser, idPlant).asLiveData()
}