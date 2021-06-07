package com.example.capstonemobile.data.source.remote

import androidx.lifecycle.LiveData
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.data.source.local.entity.UploadImage
import com.example.capstonemobile.data.source.local.entity.User
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface RemoteSource {

    fun getAllPlant(): LiveData<ApiResponse<List<Plant>>>

    fun getPlantByUserId(id: String): LiveData<ApiResponse<List<PlantDetail>>>

    fun getPlantById(id: String): LiveData<ApiResponse<Plant>>

    fun insertNewPlant(id: String,plant: PlantDetail): LiveData<ApiResponse<PlantDetail>>

    fun uploadImage(picture: MultipartBody.Part): LiveData<ApiResponse<UploadImage>>

    fun login(body: RequestBody): LiveData<ApiResponse<User>>
}