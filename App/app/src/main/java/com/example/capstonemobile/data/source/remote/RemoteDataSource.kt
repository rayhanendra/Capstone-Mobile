package com.example.capstonemobile.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstonemobile.api.ApiService
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.data.source.local.entity.UploadImage
import com.example.capstonemobile.data.source.local.entity.User
import com.ojanbelajar.moviekatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.await
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
        private val apiService: ApiService
): RemoteSource{

    override fun getAllPlant(): LiveData<ApiResponse<List<Plant>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<Plant>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getPlant().await()
                result.postValue(ApiResponse.success(response.data))
            } catch (e : IOException){
                e.printStackTrace()
                result.postValue(
                        ApiResponse.error(
                                e.message.toString(),
                                mutableListOf()
                        )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return result
    }

    override fun getPlantByUserId(id: String): LiveData<ApiResponse<List<PlantDetail>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<PlantDetail>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getPlantByUserId(id).await()
                result.postValue(ApiResponse.success(response.data))
            }catch (e: IOException){
                e.printStackTrace()
                result.postValue(
                        ApiResponse.error(
                                e.message.toString(),
                                mutableListOf()
                        )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return result
    }

    override fun getPlantById(id: String): LiveData<ApiResponse<Plant>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<Plant>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getPlantById(id).await()
                result.postValue(ApiResponse.success(response.data))
            }catch (e: IOException){
                e.printStackTrace()
                result.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        Plant()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return result
    }


    override fun insertNewPlant(id: String, plant: PlantDetail): LiveData<ApiResponse<PlantDetail>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<PlantDetail>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val respoonse = apiService.insertPlantByUserId(id,plant).await()
                result.postValue(ApiResponse.success(respoonse.data))
            } catch (e: IOException){
                e.printStackTrace()
                result.postValue(
                        ApiResponse.error(
                                e.message.toString(),
                                PlantDetail()
                        )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return result
    }

    override fun uploadImage(picture: MultipartBody.Part): LiveData<ApiResponse<UploadImage>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<UploadImage>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val respoonse = apiService.uploadPlantImage(picture).await()
                result.postValue(ApiResponse.success(respoonse.data))
            } catch (e: IOException){
                e.printStackTrace()
                result.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        UploadImage()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return result
    }

    override fun login(body: RequestBody): LiveData<ApiResponse<User>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<User>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val respoonse = apiService.login(body).await()
                result.postValue(ApiResponse.success(respoonse.data.user))
            } catch (e: IOException){
                e.printStackTrace()
                result.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        User()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return result
    }

}