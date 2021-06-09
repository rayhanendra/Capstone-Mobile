package com.example.capstonemobile.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.capstonemobile.data.source.local.entity.NPK
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Query("Select * from plants")
    fun getPlant(): Flow<List<Plant>>

    @Query("Select * from plant_detail")
    fun getPlantById(): Flow<List<PlantDetail>>

    @Query("Select * from npks")
    fun getNPK(): Flow<List<NPK>>

    @Query("SELECT * FROM plants WHERE id= :id")
    fun getPlantDetailById(id: String): Flow<Plant>

    @Insert(onConflict = OnConflictStrategy.REPLACE,entity = NPK::class)
    fun insertNpk(npk: NPK)

    @Insert(onConflict = OnConflictStrategy.REPLACE,entity = Plant::class)
    fun insertPlant(plants: List<Plant>)

    @Insert(onConflict = OnConflictStrategy.REPLACE,entity = PlantDetail::class)
    fun insertPlantDetail(plants: List<PlantDetail>)

    @Query("DELETE FROM npks")
    fun delete()


}