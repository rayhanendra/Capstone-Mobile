package com.example.capstonemobile.data.source.remote

import com.example.capstonemobile.api.ApiService
import com.example.capstonemobile.data.source.local.entity.*
import com.example.capstonemobile.data.source.remote.response.DataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
        private val apiService: ApiService
): RemoteSource{


    override suspend fun getAllPlant(): Flow<ApiResponse<List<Plant>>> {
        return flow {
            try {
                val response = apiService.getPlant()
                val data = response.data
                if (data.isNotEmpty()){
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO )
    }

    override suspend fun getPlantByUserId(id: String): Flow<ApiResponse<List<PlantDetail>>> {
        return flow {
            try {
                val response = apiService.getPlantByUserId("/api/users/${id}/plants")
                val data = response.data
                if (data.isNotEmpty()){
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getPlantById(id: String): Flow<ApiResponse<Plant>> {
        return flow {
            try {
                val response = apiService.getPlantById("/api/plants/${id}")
                val data = response.data
                if (data != null){
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllDisease(): Flow<ApiResponse<List<DiseaseEntity>>> {
        return flow {
            try {
                val response = apiService.getDisease("/api/plants/diseases")
                val data = response.data
                if (data != null){
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDiseaseByUserId(
        idUser: String,
        idPlant: String
    ): Flow<ApiResponse<List<DiseaseByUserEntity>>> {
        return flow {
            try {
                val response = apiService.getDiseaseByUser("/api/users/${idUser}/plants/${idPlant}/diseases")
                val data = response.data
                if (data != null) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }


//    override suspend fun getDiseaseById(
//        idUser: String,
//        idPlant: String,
//        id: String
//    ): Flow<ApiResponse<DiseaseEntity>> {
//        return flow {
//            try {
//                val response = apiService.getDiseaseById("/api/users/${idUser}/plants/${idPlant}/diseases/${id}")
//                val data = response.data
//                if (data != null) {
//                    emit(ApiResponse.Success(response.data))
//                } else {
//                    emit(ApiResponse.Empty)
//                }
//            } catch (e: Exception) {
//                emit(ApiResponse.Error(e.toString()))
//            }
//        } .flowOn((Dispatchers.IO))
//    }

    override suspend fun getCheckupByUserId(
        idUser: String,
        idPlant: String
    ): Flow<ApiResponse<List<CheckupEntity>>> {
        return flow {
            try {
                val response = apiService.getCheckupByUser("/api/users/${idUser}/plants/${idPlant}/diseases")
                val data = response.data
                if (data != null){
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertNewPlant(
        id: String,
        plant: PlantDetail
    ): Flow<ApiResponse<PlantDetail>> {
        return flow {
            try {
                val response = apiService.insertPlantByUserId("/api/users/${id}/plants",plant)
                val data = response.data
                if (data != null){
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun uploadImage(picture: MultipartBody.Part): Flow<ApiResponse<UploadImage>> {
        return flow {
            try {
                val response = apiService.uploadPlantImage(picture)
                val data = response.data
                if (data != null){
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun login(body: RequestBody): Flow<ApiResponse<DataResponse>> {
        return flow {
            try {
                val response = apiService.login(body)
                val data = response.data
                if (data != null){
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}