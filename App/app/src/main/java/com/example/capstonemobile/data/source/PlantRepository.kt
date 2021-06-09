package com.example.capstonemobile.data.source


import androidx.core.util.lruCache
import com.example.capstonemobile.data.source.local.LocalSource
import com.example.capstonemobile.data.source.local.entity.*
import com.example.capstonemobile.data.source.remote.ApiResponse
import com.example.capstonemobile.data.source.remote.RemoteSource
import com.example.capstonemobile.data.source.remote.response.DataResponse
import com.example.capstonemobile.utils.AppExecutors
import com.ojanbelajar.moviekatalogue.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class PlantRepository @Inject constructor(
        private val remoteSource: RemoteSource,
        private val localSource: LocalSource,
        private val appExecutors: AppExecutors
): Repository{
    override fun getPlant(): Flow<Resource<List<Plant>>> {
        return object : NetworkBoundResource<List<Plant>,List<Plant>>(){
            override fun loadFromDB(): Flow<List<Plant>> {
                return localSource.getPlant()
            }

            override fun shouldFetch(data: List<Plant>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<Plant>>> =
                remoteSource.getAllPlant()

            override suspend fun saveCallResult(data: List<Plant>) {
                appExecutors.diskIO().execute{
                    localSource.insertPlant(data)
                }
            }

        }.asFlow()
    }

    override fun getPlantById(id: String): Flow<Resource<Plant>> {
        return object : NetworkOnlyResource<Plant,Plant>(){
            override fun loadFromNetwork(data: Plant): Flow<Plant> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<Plant>> {
                return remoteSource.getPlantById(id)
            }

        }.asFlow()
    }

    override fun getPlantByUserId(id: String): Flow<Resource<List<PlantDetail>>> {
        return object : NetworkBoundResource<List<PlantDetail>,List<PlantDetail>>(){
            override fun loadFromDB(): Flow<List<PlantDetail>> {
                return localSource.getPlantById()
            }

            override fun shouldFetch(data: List<PlantDetail>?): Boolean  = true


            override suspend fun createCall(): Flow<ApiResponse<List<PlantDetail>>> {
                return remoteSource.getPlantByUserId(id)
            }

            override suspend fun saveCallResult(data: List<PlantDetail>) {
                appExecutors.diskIO().execute{
                    localSource.insertPlantDetail(data)
                }
            }

        }.asFlow()
    }

    override fun getDisease(): Flow<Resource<List<DiseaseEntity>>> {
        return object : NetworkOnlyResource<List<DiseaseEntity>,List<DiseaseEntity>>(){
            override fun loadFromNetwork(data: List<DiseaseEntity>): Flow<List<DiseaseEntity>> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<DiseaseEntity>>> {
                return remoteSource.getAllDisease()
            }
        }.asFlow()
    }

    override fun getNPK(body: RequestBody): Flow<Resource<List<NPK>>> {
       return object : NetworkBoundResource<List<NPK>,NPK>(){
           override fun loadFromDB(): Flow<List<NPK>> {
               return localSource.getNPK()
           }

           override fun shouldFetch(data: List<NPK>?): Boolean = false

           override suspend fun createCall(): Flow<ApiResponse<NPK>> {
                return remoteSource.insertNpk(body)
           }

           override suspend fun saveCallResult(data: NPK) {
               TODO("Not yet implemented")
           }

       }.asFlow()

    }

    override fun getDiseaseByUserId(
        idUser: String,
        idPlant: String
    ): Flow<Resource<List<DiseaseByUserEntity>>> {
       return object : NetworkOnlyResource<List<DiseaseByUserEntity>,List<DiseaseByUserEntity>>(){
           override fun loadFromNetwork(data: List<DiseaseByUserEntity>): Flow<List<DiseaseByUserEntity>> {
               return flowOf(data)
           }

           override suspend fun createCall(): Flow<ApiResponse<List<DiseaseByUserEntity>>> {
                return remoteSource.getDiseaseByUserId(idUser,idPlant)
           }

       }.asFlow()
    }

//    override fun getDiseaseById(idUser: String, idPlant: String, id: String) : Flow<Resource<DiseaseDetailEntity>> {
//        return object : NetworkOnlyResource<DiseaseDetailEntity, DiseaseDetailEntity>() {
//            override fun loadFromNetwork(data: DiseaseDetailEntity): Flow<DiseaseDetailEntity> {
//                return flowOf(data)
//            }
//
//            override suspend fun createCall(): Flow<ApiResponse<DiseaseDetailEntity>> {
//                return remoteSource.getDiseaseById(idUser, idPlant, id)
//            }
//        }.asFlow()
//    }


    override fun insertNewPlant(id: String, plant: PlantDetail): Flow<Resource<PlantDetail>> {
        return object : NetworkOnlyResource<PlantDetail,PlantDetail>(){
            override fun loadFromNetwork(data: PlantDetail): Flow<PlantDetail> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<PlantDetail>> {
                return remoteSource.insertNewPlant(id,plant)
            }
        }.asFlow()
    }

    override fun insertNPK(body: RequestBody): Flow<Resource<NPK>> {
        return object : NetworkOnlyResource<NPK,NPK>(){
            override fun loadFromNetwork(data: NPK): Flow<NPK> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<NPK>> {
                return remoteSource.insertNpk(body)
            }

        }.asFlow()
    }

    override fun saveNpk(npk: NPK) {
        appExecutors.diskIO().execute{
            localSource.delete()
            localSource.insertNPK(npk)
        }
    }


    override fun uploadImage(picture: MultipartBody.Part): Flow<Resource<UploadImage>> {
        return object : NetworkOnlyResource<UploadImage,UploadImage>(){
            override fun loadFromNetwork(data: UploadImage): Flow<UploadImage> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<UploadImage>> {
                return remoteSource.uploadImage(picture)
            }
        }.asFlow()
    }

    override fun login(body: RequestBody): Flow<Resource<User>> {
        return object : NetworkOnlyResource<User,DataResponse>(){
            override fun loadFromNetwork(data: DataResponse): Flow<User> {
                return flowOf(data.user)
            }

            override suspend fun createCall(): Flow<ApiResponse<DataResponse>> {
                return remoteSource.login(body)
            }

        }.asFlow()
    }

}