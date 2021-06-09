package com.example.capstonemobile.ui.mygarden.checkup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonemobile.data.source.Repository
import com.example.capstonemobile.data.source.local.entity.NPK
import okhttp3.RequestBody


class CheckupViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel() {

    fun getPlantDetail(id: String) = repository.getPlantById(id).asLiveData()

    fun insertNpk(body: RequestBody) = repository.insertNPK(body).asLiveData()

    fun saveNpk(npk:NPK) = repository.saveNpk(npk)
}