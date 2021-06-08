package com.example.capstonemobile.data.source.remote

import com.example.capstonemobile.data.source.local.entity.*
import com.example.capstonemobile.data.source.remote.response.DataResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface RemoteSource {

    suspend fun getAllPlant(): Flow<ApiResponse<List<Plant>>>

    suspend fun getPlantByUserId(id: String): Flow<ApiResponse<List<PlantDetail>>>

    suspend fun getPlantById(id: String): Flow<ApiResponse<Plant>>

    suspend fun getDiseaseByUser(idUser: String,idPlant: String): Flow<ApiResponse<List<DiseaseDetailEntity>>>

    suspend fun getDiseaseById(idUser: String, idPlant: String, id: String): Flow<ApiResponse<DiseaseDetailEntity>>

    suspend fun insertNewPlant(id: String,plant: PlantDetail): Flow<ApiResponse<PlantDetail>>

    suspend fun uploadImage(picture: MultipartBody.Part): Flow<ApiResponse<UploadImage>>

    suspend fun login(body: RequestBody): Flow<ApiResponse<DataResponse>>
}