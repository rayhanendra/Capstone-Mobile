package com.example.capstonemobile.api

import com.example.capstonemobile.data.source.remote.response.InsertNewPlantResponse
import com.example.capstonemobile.data.source.remote.response.PlantDetailResponse
import com.example.capstonemobile.data.source.remote.response.PlantResponse
import com.example.capstonemobile.data.source.remote.response.UserResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET("api/plants")
    fun getPlant(): Call<PlantResponse>

    @GET("api/users/{userId}/plants")
    fun getPlantByUserId(@Path("userId") userId: String): Call<PlantDetailResponse>

    @POST("api/users/{userId}/plants")
    fun insertPlantByUserId(@Path("userId") userId: String, @Body body: RequestBody): Call<InsertNewPlantResponse>

    @POST("api/user/_sign-in")
    fun login(@Body body: RequestBody): Call<UserResponse>

}