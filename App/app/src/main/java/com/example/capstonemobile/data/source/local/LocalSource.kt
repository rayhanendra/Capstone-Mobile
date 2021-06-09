package com.example.capstonemobile.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.capstonemobile.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

interface LocalSource {


    fun getPlant(): Flow<List<Plant>>

    fun getPlantById(): Flow<List<PlantDetail>>

    fun getNPK(): List<NPK>

    fun getPlantDetail(id: String): Flow<Plant>

    fun insertNPK(npk: NPK)

    fun insertPlantDetail(plants: List<PlantDetail>)

    fun insertPlant(plants: List<Plant>)

    fun delete()


}