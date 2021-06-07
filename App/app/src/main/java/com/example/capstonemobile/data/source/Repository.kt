package com.example.capstonemobile.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.data.source.local.entity.UploadImage
import com.example.capstonemobile.data.source.local.entity.User
import com.ojanbelajar.moviekatalogue.utils.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface Repository {
    fun getPlant(): LiveData<Resource<List<Plant>>>

    fun getPlantById(id: String): LiveData<Resource<Plant>>

    fun getPlantByUserId(id: String): LiveData<Resource<PagedList<PlantDetail>>>

    fun insertNewPlant(id: String, plant: PlantDetail): LiveData<Resource<PlantDetail>>

    fun uploadImage(picture: MultipartBody.Part): LiveData<Resource<UploadImage>>

    fun login(body: RequestBody): LiveData<Resource<User>>
}