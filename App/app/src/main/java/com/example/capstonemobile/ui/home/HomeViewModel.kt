package com.example.capstonemobile.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.capstonemobile.data.source.Repository


class HomeViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getAllPlant() = repository.getPlant()

    fun getPlantByUserId(id: String) = repository.getPlantByUserId(id)

}