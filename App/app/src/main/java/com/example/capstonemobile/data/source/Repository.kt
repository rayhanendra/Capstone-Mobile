package com.example.capstonemobile.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.ojanbelajar.moviekatalogue.utils.Resource
import okhttp3.RequestBody

interface Repository {
    fun getPlant(): LiveData<Resource<PagedList<Plant>>>

    fun getPlantByUserId(id: String): LiveData<Resource<PagedList<PlantDetail>>>

    fun insertNewPlant(id: String, body: RequestBody): LiveData<Resource<PlantDetail>>
}