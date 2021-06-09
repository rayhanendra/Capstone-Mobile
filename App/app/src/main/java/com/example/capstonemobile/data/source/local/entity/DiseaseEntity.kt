package com.example.capstonemobile.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "diseases")
@Parcelize
data class DiseaseEntity(
        @PrimaryKey
        @ColumnInfo(name = "id")
        @NonNull
        var id: String = "",

        @ColumnInfo(name = "diseaseName")
        var diseaseName: String = "",

        @ColumnInfo(name = "diseaseDetail")
        var diseaseDetail: String = "",

        @ColumnInfo(name = "diseaseTreatment")
        var diseaseTreatment: String = "",

        @ColumnInfo(name = "diseaseTreatmentVid")
        var diseaseTreatmentVid: String = "",

        @ColumnInfo(name = "createdAt")
        var createdAt: String = "",

        @ColumnInfo(name = "updatedAt")
        var updatedAt: String = ""
):Parcelable
