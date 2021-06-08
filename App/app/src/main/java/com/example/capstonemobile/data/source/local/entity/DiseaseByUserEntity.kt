package com.example.capstonemobile.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DiseaseByUserEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    var id: String = "",

    @ColumnInfo(name = "userPlantId")
    var userPlantId: String = "",

    @ColumnInfo(name = "plantDiseaseId")
    var plantDiseaseId: String = "",

    @ColumnInfo(name = "createdAt")
    var createdAt: String = "",

    @ColumnInfo(name = "updatedAt")
    var updatedAt: String = ""
): Parcelable