package com.example.capstonemobile.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonemobile.data.source.Repository


class HomeViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val plants = repository.getPlant().asLiveData()

    fun getPlantByUserId(id: String) = repository.getPlantByUserId(id).asLiveData()

}