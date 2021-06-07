package com.example.capstonemobile.data.source


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

            override fun shouldFetch(data: List<PlantDetail>?): Boolean  = data == null || data.isEmpty()


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

    override fun getDiseaseByUser(
        idUser: String,
        idPlant: String
    ): Flow<Resource<List<DiseaseDetail>>> {
       return object : NetworkOnlyResource<List<DiseaseDetail>,List<DiseaseDetail>>(){
           override fun loadFromNetwork(data: List<DiseaseDetail>): Flow<List<DiseaseDetail>> {
               return flowOf(data)
           }

           override suspend fun createCall(): Flow<ApiResponse<List<DiseaseDetail>>> {
                return remoteSource.getDiseaseByUser(idUser,idPlant)
           }

       }.asFlow()
    }

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