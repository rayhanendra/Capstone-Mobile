package com.example.capstonemobile.ui.disease

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonemobile.data.source.Repository

class DiseaseViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel() {

    val diseases = repository.getDisease().asLiveData()

}