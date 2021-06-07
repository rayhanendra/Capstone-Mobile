package com.example.capstonemobile.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.capstonemobile.data.source.local.LocalSource
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.data.source.local.entity.UploadImage
import com.example.capstonemobile.data.source.local.entity.User
import com.example.capstonemobile.data.source.remote.ApiResponse
import com.example.capstonemobile.data.source.remote.RemoteSource
import com.example.capstonemobile.data.source.remote.StatusResponse
import com.ojanbelajar.moviekatalogue.utils.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class PlantRepository @Inject constructor(
        private val remoteSource: RemoteSource,
        private val localSource: LocalSource
): Repository{

    override fun getPlant(): LiveData<Resource<List<Plant>>> {
        return object : NetworkBoundResource<List<Plant>,List<Plant>>() {
            override fun loadFromDB(): LiveData<List<Plant>> {
                return localSource.getPlant()
            }

            override fun shouldFetch(data: List<Plant>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Plant>>> =
                    remoteSource.getAllPlant()

            override fun saveCallResult(data: List<Plant>) {
                val list = ArrayList<Plant>()
                for( response in data){
                    val result = Plant(
                            response.id,
                            response.plantName,
                            response.plantClass,
                            response.plantSpecies,
                            response.plantHumidity,
                            response.plantLighting,
                            response.plantFertilizer,
                            response.plantPrice,
                            response.plantSalesPrice,
                            response.plantMonthHarvest,
                            response.plantPreparation
                    )
                    list.add(result)
                }
            localSource.insertPlant(list)
            }
        }.asLiveData()
    }



    override fun getPlantById(id: String): LiveData<Resource<Plant>> {
        val result = MediatorLiveData<Resource<Plant>>()
        val apiResponse = remoteSource.getPlantById(id)
        result.addSource(apiResponse) { response ->
            when (response.status) {
                StatusResponse.SUCCESS -> {
                    result.addSource(result){
                        result.value = it
                    }
                }

                StatusResponse.ERROR -> {
                    result.addSource(result){
                        result.value = it
                    }
                }
            }
        }
        return result
    }

    //seharusnya ini satu object karena buat detail di responsenya juga satu object
    override fun getPlantByUserId(id: String): LiveData<Resource<PagedList<PlantDetail>>> {
        return object : NetworkBoundResource<PagedList<PlantDetail>, List<PlantDetail>>() {
            override fun loadFromDB(): LiveData<PagedList<PlantDetail>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localSource.getPlantById(),config).build()
            }

            override fun shouldFetch(data: PagedList<PlantDetail>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<PlantDetail>>> =
                    remoteSource.getPlantByUserId(id)

            override fun saveCallResult(data: List<PlantDetail>) {
                val list = ArrayList<PlantDetail>()
                for (response in data) {
                    val result = PlantDetail(
                            response.id,
                            response.plantId,
                            response.userId,
                            response.userPlantName,
                            response.plantHealth,
                            response.plantImage,
                            response.plantDetail,
                            response.plantPhase,
                            response.plantSuggestion,
                            response.createdAt
                    )
                    list.add(result)
                }
                localSource.insertPlantDetail(list)
            }
        }.asLiveData()
    }

    override fun insertNewPlant(id: String, plant: PlantDetail): LiveData<Resource<PlantDetail>> {
        val result = MediatorLiveData<Resource<PlantDetail>>()
        val apiResponse = remoteSource.insertNewPlant(id, plant)
        result.addSource(apiResponse) { response ->
            when (response.status) {
                StatusResponse.SUCCESS -> {
                   result.addSource(result){
                       result.value = it
                   }
                }

                StatusResponse.ERROR -> {
                    result.addSource(result){
                        result.value = it
                    }
                }
            }
        }
        return result
    }

    override fun uploadImage(picture: MultipartBody.Part): LiveData<Resource<UploadImage>> {
        val result = MediatorLiveData<Resource<UploadImage>>()
        val apiResponse = remoteSource.uploadImage(picture)
        result.addSource(apiResponse) { response ->
            when (response.status) {
                StatusResponse.SUCCESS -> {
                    result.addSource(result){
                        result.value = it
                    }
                }

                StatusResponse.ERROR -> {
                    result.addSource(result){
                        result.value = it
                    }
                }
            }
        }
        return result
    }

    override fun login(body: RequestBody): LiveData<Resource<User>> {

        val result = MediatorLiveData<Resource<User>>()
        val apiResponse = remoteSource.login(body)
        result.addSource(apiResponse){ response ->
            when(response.status){
                StatusResponse.SUCCESS -> {
                    result.addSource(result){
                        result.value = it
                    }
                }
                StatusResponse.ERROR -> {
                    result.addSource(result){
                        result.value = it
                    }
                }
            }
        }
        return result
    }
}