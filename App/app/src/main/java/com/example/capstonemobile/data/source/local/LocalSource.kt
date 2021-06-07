package com.example.capstonemobile.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.data.source.local.entity.UploadImage
import com.example.capstonemobile.data.source.local.entity.User
import kotlinx.coroutines.flow.Flow

interface LocalSource {


    fun getPlant(): Flow<List<Plant>>

    fun getPlantById(): Flow<List<PlantDetail>>

    fun getPlantDetail(id: String): Flow<Plant>

    fun insertPlantDetail(plants: List<PlantDetail>)

    fun insertPlant(plants: List<Plant>)


}