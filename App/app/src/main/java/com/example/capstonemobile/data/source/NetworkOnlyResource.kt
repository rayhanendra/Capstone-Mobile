package com.example.capstonemobile.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.capstonemobile.data.source.remote.ApiResponse
import com.example.capstonemobile.data.source.remote.StatusResponse
import com.ojanbelajar.moviekatalogue.utils.Resource
import com.ojanbelajar.moviekatalogue.utils.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkOnlyResource<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        val apiResponse = createCall()
        when(apiResponse.status){
            StatusResponse.SUCCESS ->  CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    result.value = Resource.success(loadFromNetwork(apiResponse.body))
                }
            }
            StatusResponse.ERROR -> {
                result.value = Resource.error(apiResponse.message.toString(),loadFromNetwork(apiResponse.body))

            }
        }

    }


    protected abstract fun loadFromNetwork(data: RequestType): ResultType

    protected abstract  fun createCall(): ApiResponse<RequestType>

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}