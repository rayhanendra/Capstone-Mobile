package com.example.capstonemobile.api

import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.data.source.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface ApiService {

    @GET("/api/plants")
    suspend fun getPlant(): PlantResponse

    @GET
    suspend fun getPlantByUserId(@Url url: String): UserPlantResponse

    @GET
    suspend fun getPlantById(@Url url: String): PlantDetailResponse

    @GET
    suspend fun getDisease(@Url url: String): DiseaseResponse

    @GET
    suspend fun getDiseaseByUser(@Url url: String): DiseaseByUserResponse

    @GET
    suspend fun getDiseaseById(@Url url: String): DiseaseDetailResponse

    @GET
    suspend fun getCheckupByUser(@Url url: String) : CheckupByUserResponse

    @POST
    suspend fun insertPlantByUserId(@Url url: String , @Body plant: PlantDetail): InsertNewPlantResponse

    @POST("/api/user/_sign-in")
    suspend fun login(@Body body: RequestBody): UserResponse

    @POST("https://asia-southeast1-ml.googleapis.com/v1/projects/yubisayu/models/fertilizer_recommender:predict")
    suspend fun npk(@Body body: RequestBody): NPKResponse

    @Multipart
    @POST("/api/upload/plants")
    suspend fun uploadPlantImage(@Part picture: MultipartBody.Part): CameraResponse

}