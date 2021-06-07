package com.example.capstonemobile.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.data.source.local.entity.UploadImage
import com.example.capstonemobile.data.source.local.entity.User
import com.example.capstonemobile.data.source.local.room.PlantDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(
        private val dao: PlantDao
): LocalSource{

    override fun getPlant() = dao.getPlant()

    override fun getPlantById() = dao.getPlantById()

    override fun getPlantDetail(id: String) = dao.getPlantDetailById(id)

    override fun insertPlantDetail(plants: List<PlantDetail>) = dao.insertPlantDetail(plants)

    override fun insertPlant(plants: List<Plant>) = dao.insertPlant(plants)


}