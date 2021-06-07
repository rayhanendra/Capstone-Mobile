package com.example.capstonemobile.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail

interface LocalSource {

    fun getPlant(): LiveData<List<Plant>>

    fun getPlantById(): DataSource.Factory<Int,PlantDetail>

    fun getPlantDetail(id: String): LiveData<Plant>

    fun insertPlantDetail(plants: List<PlantDetail>)

    fun insertPlant(plants: List<Plant>)
}