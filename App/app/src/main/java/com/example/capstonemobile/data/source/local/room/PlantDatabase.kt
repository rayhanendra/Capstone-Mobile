package com.example.capstonemobile.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstonemobile.data.source.local.entity.*

@Database(entities = [CheckupEntity::class,DiseaseEntity::class,DiseaseDetailEntity::class, Plant::class, PlantDetail::class,User::class,NPK::class],version = 10,exportSchema = false)
abstract class PlantDatabase: RoomDatabase(){
    abstract fun plantDao(): PlantDao

    companion object{
        @Volatile
        private var Instance: PlantDatabase? = null

        fun getInstance(context: Context): PlantDatabase =
                Instance ?: synchronized(this){
                    Room.databaseBuilder(
                            context.applicationContext,
                            PlantDatabase::class.java,
                            "plant.db"
                    ).fallbackToDestructiveMigration()
                            .build().apply {
                                Instance = this
                            }
                }
    }
}