package com.example.capstonemobile.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail

@Dao
interface PlantDao {

    @Query("Select * from plants")
    fun getPlant(): LiveData<List<Plant>>

    @Query("Select * from plant_detail")
    fun getPlantById(): DataSource.Factory<Int, PlantDetail>

    @Query("SELECT * FROM plants WHERE id= :id")
    fun getPlantDetailById(id: String): LiveData<Plant>

    @Insert(onConflict = OnConflictStrategy.REPLACE,entity = Plant::class)
    fun insertPlant(plants: List<Plant>)


    @Insert(onConflict = OnConflictStrategy.REPLACE,entity = PlantDetail::class)
    fun insertPlantDetail(plants: List<PlantDetail>)

}