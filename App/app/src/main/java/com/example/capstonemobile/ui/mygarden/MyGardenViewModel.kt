package com.example.capstonemobile.ui.mygarden

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.capstonemobile.data.source.Repository
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.ojanbelajar.moviekatalogue.utils.Resource

class MyGardenViewModel  @ViewModelInject constructor(
        private val repository: Repository
): ViewModel() {


    fun getPlantByUserId(id: String): LiveData<Resource<PagedList<PlantDetail>>> = repository.getPlantByUserId(id)

}