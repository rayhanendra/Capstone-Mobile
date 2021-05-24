package com.example.capstonemobile.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "plants")
@Parcelize
data class Plant(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String = "",

    @ColumnInfo(name = "plantName")
    var plantName: String = "",

    @ColumnInfo(name = "plantClass")
    var plantClass: String = "",

    @ColumnInfo(name = "plantSpecies")
    var plantSpecies: String = "",

    @ColumnInfo(name = "plantHumidity")
    var plantHumidity: Double = 0.0,

    @ColumnInfo(name = "plantLighting")
    var plantLighting: Double = 0.0,

    @ColumnInfo(name = "plantFertilizer")
    var plantFertilizer: Double = 0.0,

    @ColumnInfo(name = "plantPrice")
    var plantPrice: Double = 0.0,

    @ColumnInfo(name = "plantSalesPrice")
    var plantSalesPrice: Double = 0.0,

    @ColumnInfo(name = "plantMonthHarvest")
    var plantMonthHarvest: String = "",

    @ColumnInfo(name = "plantPreparation")
    var plantPreparation: String = ""

): Parcelable
