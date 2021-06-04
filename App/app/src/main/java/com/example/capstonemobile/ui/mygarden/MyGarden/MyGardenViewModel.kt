package com.example.capstonemobile.ui.mygarden.MyGarden

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.example.capstonemobile.data.source.Repository
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.data.source.local.entity.UserPlantEntity
import com.example.capstonemobile.data.source.remote.response.PlantResponse
import com.ojanbelajar.moviekatalogue.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class MyGardenViewModel  @ViewModelInject constructor(
        private val repository: Repository
): ViewModel() {

//    fun getPlantByUserId(id: String): LiveData<Resource<PagedList<PlantDetail>>> = repository.getPlantByUserId(id)

    fun getAllUserPlant(): LiveData<Resource<PagedList<UserPlantEntity>>> = repository.getAllUserPlant()
}
