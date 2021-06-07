package com.example.capstonemobile.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UploadImage(
    var imageUrl: String = ""
): Parcelable
