package com.example.capstonemobile.di

import android.app.Application
import android.content.Context
import com.example.capstonemobile.api.ApiService
import com.example.capstonemobile.data.source.PlantRepository
import com.example.capstonemobile.data.source.Repository
import com.example.capstonemobile.data.source.local.LocalDataSource
import com.example.capstonemobile.data.source.local.LocalSource
import com.example.capstonemobile.data.source.local.room.PlantDao
import com.example.capstonemobile.data.source.local.room.PlantDatabase
import com.example.capstonemobile.data.source.remote.RemoteDataSource
import com.example.capstonemobile.data.source.remote.RemoteSource
import com.example.capstonemobile.utils.AppExecutors
import com.example.capstonemobile.utils.SessionManagement
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideBase() = "https://yubisayu.et.r.appspot.com"

    @Provides
    @Singleton
    fun provideAppExecutor() = AppExecutors()

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
    }.build()


    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor =
            Interceptor { chain ->
                chain.proceed(chain.request().newBuilder()
                    .addHeader("Authorization", "ya29.a0AfH6SMAtySlWeGc7SmpwOW-SHWHm9Oept8shttgtEYpCuw4mPGikke7H99wShB7I3FP-jyrleWBkc8KvcgJ73cxmx6499Ckmvj0iaJiNyMd0HYhlY_reig93y6QpmaJz6RFcxE-6lpYwUhLaW04HkjDQOD4Y3w")
                    .build())

        }

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient, BASEURL: String,interceptor: Interceptor): Retrofit = Retrofit.Builder().apply {
        baseUrl(BASEURL)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideContentRemoteSource(remoteSource: RemoteDataSource): RemoteSource = remoteSource

    @Provides
    @Singleton
    fun provideContentLocalSource(localSource: LocalDataSource): LocalSource =  localSource

    @Provides
    @Singleton
    fun provideContentRepository(
            contentRemoteSource: RemoteSource,
            contentLocalSource: LocalSource,
            appExecutors: AppExecutors
    ): Repository = PlantRepository(contentRemoteSource,contentLocalSource,appExecutors)

    @Singleton
    @Provides
    fun provideContentDatabase(
            application: Application
    ): PlantDatabase = PlantDatabase.getInstance(application)

    @Singleton
    @Provides
    fun contentDao(db: PlantDatabase): PlantDao = db.plantDao()


}