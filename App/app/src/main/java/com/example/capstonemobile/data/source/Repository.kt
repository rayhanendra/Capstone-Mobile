package com.example.capstonemobile.data.source

import com.example.capstonemobile.data.source.local.entity.*
import com.ojanbelajar.moviekatalogue.utils.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface Repository {
    fun getPlant(): Flow<Resource<List<Plant>>>

    fun getPlantById(id: String): Flow<Resource<Plant>>

    fun getPlantByUserId(id: String): Flow<Resource<List<PlantDetail>>>

    fun getDisease(): Flow<Resource<List<DiseaseEntity>>>

    fun getNPK(body: RequestBody): Flow<Resource<List<NPK>>>

    fun getDiseaseByUserId(idUser: String,idPlant: String): Flow<Resource<List<DiseaseByUserEntity>>>

//    fun getDiseaseById(idUser: String, idPlant: String, id: String): Flow<Resource<DiseaseDetailEntity>>

//    fun getCheckupByUserId(idUser: String, idPlant: String) :

    fun insertNewPlant(id: String, plant: PlantDetail): Flow<Resource<PlantDetail>>

    fun insertNPK(body: RequestBody): Flow<Resource<NPK>>

    fun saveNpk(npk: NPK)

    fun uploadImage(picture: MultipartBody.Part): Flow<Resource<UploadImage>>

    fun login(body: RequestBody): Flow<Resource<User>>

}