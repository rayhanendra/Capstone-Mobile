package com.example.capstonemobile.api

import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.data.source.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET("api/plants")
    fun getPlant(): Call<PlantResponse>

    @GET("api/users/{userId}/plants")
    fun getPlantByUserId(@Path("userId") userId: String): Call<UserPlantResponse>

    @GET("api/plants/{plantId}")
    fun getPlantById(@Path("plantId") plantId: String): Call<PlantDetailResponse>

    @POST("api/users/{userId}/plants")
    fun insertPlantByUserId(@Path("userId") userId: String,@Body plant: PlantDetail): Call<InsertNewPlantResponse>

    @POST("api/user/_sign-in")
    fun login(@Body body: RequestBody): Call<UserResponse>

    @Multipart
    @POST("api/upload/plants")
    fun uploadPlantImage(@Part picture: MultipartBody.Part): Call<CameraResponse>

}