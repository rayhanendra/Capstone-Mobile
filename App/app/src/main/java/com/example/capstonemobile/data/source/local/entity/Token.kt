package com.example.capstonemobile.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Token (
    val access: String = "",
    val refresh: String = ""
): Parcelable