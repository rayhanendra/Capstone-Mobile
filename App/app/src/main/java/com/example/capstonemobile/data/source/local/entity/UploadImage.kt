package com.example.capstonemobile.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "picture")
data class UploadImage(
    var imageUrl: String = ""
): Parcelable
