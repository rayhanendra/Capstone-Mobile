package com.example.capstonemobile.data.source.local

import androidx.paging.DataSource
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail

interface LocalSource {

    fun getPlant(): DataSource.Factory<Int,Plant>

    fun getPlantById(): DataSource.Factory<Int,PlantDetail>

    fun insertPlantById(plants: List<PlantDetail>)

    fun insertPlant(plants: List<Plant>)
}