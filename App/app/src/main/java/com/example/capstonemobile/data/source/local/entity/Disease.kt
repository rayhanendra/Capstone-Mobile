package com.example.capstonemobile.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "diseases")
@Parcelize
data class Disease(
        @PrimaryKey
        @ColumnInfo(name = "id")
        @NonNull
        var id: String = "",

        @ColumnInfo(name = "diseasesName")
        var diseasesName: String = "",

        @ColumnInfo(name = "diseasesDetail")
        var diseasesDetail: String = "",

        @ColumnInfo(name = "diseasesTreatment")
        var diseasesTreatment: String = "",

        @ColumnInfo(name = "diseasesTreatmentVideos")
        var diseasesTreatmentVideos: String = ""
):Parcelable
