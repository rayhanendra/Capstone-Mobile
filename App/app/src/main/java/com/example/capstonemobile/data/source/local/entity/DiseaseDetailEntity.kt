package com.example.capstonemobile.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "disease_detail")
@Parcelize
data class DiseaseDetailEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    var id: String = "",

    @ColumnInfo(name = "userPlantId")
    var userPlantId: String = "",

    @ColumnInfo(name = "planDiseaseId")
    var planDiseaseId: String = "",

): Parcelable