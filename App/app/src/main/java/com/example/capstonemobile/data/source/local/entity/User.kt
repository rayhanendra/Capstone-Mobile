package com.example.capstonemobile.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "users")
@Parcelize
data class User(
        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: String = "",

        @ColumnInfo(name = "fullName")
        var fullName: String = "",

        @ColumnInfo(name = "email")
        var email: String = "",

        @ColumnInfo(name = "password")
        var password: String = "",

        @ColumnInfo(name = "profilePicture")
        var profilePicture: String = "",

        @ColumnInfo(name = "address")
        var address: String = "",

        @ColumnInfo(name = "city")
        var city: String = "",

        @ColumnInfo(name = "sex")
        var sex: Int = 0,

        @ColumnInfo(name = "birthDate")
        var birthDate: String = "",
): Parcelable
