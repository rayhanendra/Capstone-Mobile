package com.example.capstonemobile.ui.mygarden.MyGarden

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonemobile.data.source.Repository


class MyGardenViewModel  @ViewModelInject constructor(
        private val repository: Repository
): ViewModel() {

//    fun getPlantByUserId(id: String): LiveData<Resource<PagedList<PlantDetail>>> = repository.getPlantByUserId(id)

    fun getAllUserPlant(id: String) = repository.getPlantByUserId(id).asLiveData()

}
