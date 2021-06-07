package com.example.capstonemobile.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "users")
@Parcelize
data class User(
        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: String = "",

        @ColumnInfo(name = "fullName")
        var fullname: String = "",

        @ColumnInfo(name = "image")
        var image: String = "",

        @ColumnInfo(name = "address")
        var address: String = "",

        @ColumnInfo(name = "city")
        var city: String = "",

        @ColumnInfo(name = "sex")
        var sex: Int = 0,

        @ColumnInfo(name = "birthDate")
        var role: String = "",
): Parcelable
