package com.example.capstonemobile.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "checkups")
@Parcelize
data class Checkup(
        @PrimaryKey
        @ColumnInfo(name = "id")
        @NonNull
        var id: String = "",

        @ColumnInfo(name = "userPlantId")
        var userPlantId: String = "",

        @ColumnInfo(name = "plantHumidity")
        var plantHumidity: Double = 0.0,

        @ColumnInfo(name = "plantTemperature")
        var plantTemperature : Double = 0.0,

        @ColumnInfo(name = "plantNitrogen")
        var plantNitrogen : Double = 0.0,

        @ColumnInfo(name = "plantPotassium")
        var plantPotassium: Double = 0.0,

        @ColumnInfo(name = "plantPhosphorus")
        var plantPhosphorus: Double = 0.0
): Parcelable
