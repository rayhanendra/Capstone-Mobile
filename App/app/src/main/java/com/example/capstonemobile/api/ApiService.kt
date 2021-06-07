package com.example.capstonemobile.api

import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.data.source.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET("/api/plants")
    suspend fun getPlant(): PlantResponse

    @GET("/api/users/{userId}/plants")
    suspend fun getPlantByUserId(@Path("userId") userId: String): UserPlantResponse

    @GET("/api/plants/{plantId}")
    suspend fun getPlantById(@Path("plantId") plantId: String): PlantDetailResponse

    @POST("/api/users/{userId}/plants")
    suspend fun insertPlantByUserId(@Path("userId") userId: String,@Body plant: PlantDetail): InsertNewPlantResponse

    @POST("/api/user/_sign-in")
    suspend fun login(@Body body: RequestBody): UserResponse

    @Multipart
    @POST("/api/upload/plants")
    suspend fun uploadPlantImage(@Part picture: MultipartBody.Part): CameraResponse

}