package com.example.capstonemobile.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.capstonemobile.data.source.local.entity.*
import com.ojanbelajar.moviekatalogue.utils.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface Repository {
    fun getPlant(): Flow<Resource<List<Plant>>>

    fun getPlantById(id: String): Flow<Resource<Plant>>

    fun getPlantByUserId(id: String): Flow<Resource<List<PlantDetail>>>

    fun getDiseaseByUser(idUser: String,idPlant: String): Flow<Resource<List<DiseaseDetail>>>

    fun insertNewPlant(id: String, plant: PlantDetail): Flow<Resource<PlantDetail>>

    fun uploadImage(picture: MultipartBody.Part): Flow<Resource<UploadImage>>

    fun login(body: RequestBody): Flow<Resource<User>>
}