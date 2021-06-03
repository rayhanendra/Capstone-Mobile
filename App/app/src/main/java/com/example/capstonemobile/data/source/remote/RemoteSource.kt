package com.example.capstonemobile.data.source.remote

import androidx.lifecycle.LiveData
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.data.source.local.entity.User
import okhttp3.RequestBody

interface RemoteSource {

    fun getAllPlant(): LiveData<ApiResponse<List<Plant>>>

    fun getPlantByUserId(id: String): LiveData<ApiResponse<List<PlantDetail>>>

    fun insertNewPlant(id: String,body: RequestBody): LiveData<ApiResponse<PlantDetail>>

    fun login(body: RequestBody): LiveData<ApiResponse<User>>
}