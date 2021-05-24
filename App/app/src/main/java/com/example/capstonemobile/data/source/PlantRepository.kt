package com.example.capstonemobile.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.capstonemobile.data.source.local.LocalSource
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.data.source.remote.ApiResponse
import com.example.capstonemobile.data.source.remote.RemoteSource
import com.example.capstonemobile.data.source.remote.StatusResponse
import com.ojanbelajar.moviekatalogue.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.RequestBody
import javax.inject.Inject

class PlantRepository @Inject constructor(
        private val remoteSource: RemoteSource,
        private val localSource: LocalSource
): Repository{

    override fun getPlant(): LiveData<Resource<PagedList<Plant>>> {
        return object : NetworkBoundResource<PagedList<Plant>,List<Plant>>() {
            override fun loadFromDB(): LiveData<PagedList<Plant>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localSource.getPlant(),config).build()
            }

            override fun shouldFetch(data: PagedList<Plant>?): Boolean =
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
                            response.plantSuggestion
                    )
                    list.add(result)
                }
                localSource.insertPlantById(list)
            }
        }.asLiveData()
    }

    override fun insertNewPlant(id: String, body: RequestBody): LiveData<Resource<PlantDetail>> {
        val result = MediatorLiveData<Resource<PlantDetail>>()
        val apiResponse = remoteSource.insertNewPlant(id, body)
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
}