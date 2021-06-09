package com.example.capstonemobile.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.capstonemobile.data.source.local.entity.*
import com.example.capstonemobile.data.source.local.room.PlantDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
        private val dao: PlantDao
): LocalSource{

    override fun getPlant() = dao.getPlant()

    override fun getPlantById() = dao.getPlantById()

    override fun getNPK() = dao.getNPK()

    override fun getPlantDetail(id: String) = dao.getPlantDetailById(id)

    override fun insertNPK(npk: NPK) = dao.insertNpk(npk)

    override fun insertPlantDetail(plants: List<PlantDetail>) = dao.insertPlantDetail(plants)

    override fun insertPlant(plants: List<Plant>) = dao.insertPlant(plants)

    override fun delete() = dao.delete()

}