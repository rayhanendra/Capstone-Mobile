package com.example.capstonemobile.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "plant_detail")
@Parcelize
data class PlantDetail(
        @PrimaryKey
        @ColumnInfo(name = "id")
        @NonNull
        var id: String = "",

        @ColumnInfo(name = "plantId")
        var plantId: String = "",

        @ColumnInfo(name = "userId")
        var userId: String = "",

        @ColumnInfo(name = "plantName")
        var plantName: String = "",

        @ColumnInfo(name = "plantHealth")
        var plantHealth: Double = 0.0,

        @ColumnInfo(name = "plantImg")
        var plantImg: String = "",

        @ColumnInfo(name = "plantDetail")
        var plantDetail: String = "",

        @ColumnInfo(name = "plantPhase")
        var plantPhase: String = "",

        @ColumnInfo(name = "plantSuggest")
        var plantSuggest: String = "",

        @ColumnInfo(name = "createdAt")
        var createdAt: Double = 0.0
): Parcelable
