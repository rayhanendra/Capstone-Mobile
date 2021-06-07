package com.example.capstonemobile.data.source.remote

import androidx.lifecycle.LiveData
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.data.source.local.entity.UploadImage
import com.example.capstonemobile.data.source.local.entity.User
import com.example.capstonemobile.data.source.remote.response.DataResponse
import com.example.capstonemobile.data.source.remote.response.PlantResponse
import com.example.capstonemobile.data.source.remote.response.UserPlantResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface RemoteSource {

    suspend fun getAllPlant(): Flow<ApiResponse<List<Plant>>>

    suspend fun getPlantByUserId(id: String): Flow<ApiResponse<List<PlantDetail>>>

    suspend fun getPlantById(id: String): Flow<ApiResponse<Plant>>

    suspend fun insertNewPlant(id: String,plant: PlantDetail): Flow<ApiResponse<PlantDetail>>

    suspend fun uploadImage(picture: MultipartBody.Part): Flow<ApiResponse<UploadImage>>

    suspend fun login(body: RequestBody): Flow<ApiResponse<DataResponse>>
}