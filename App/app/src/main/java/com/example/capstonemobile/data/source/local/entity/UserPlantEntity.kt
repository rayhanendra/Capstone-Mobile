package com.example.capstonemobile.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user_plant")
@Parcelize
class UserPlantEntity (
    @PrimaryKey
    @NonNull

    @ColumnInfo(name = "plantId")
    var plantId: String = "",

    @ColumnInfo(name = "plantName")
    var plantName: String = "",

    @ColumnInfo(name = "plantImg")
    var plantImg: String = "",

    @ColumnInfo(name = "plantPhase")
    var plantPhase: String = "",

    @ColumnInfo(name = "createdAt")
    var createdAt: String = "",

) : Parcelable