package com.example.capstonemobile.data.source.remote.response

import android.os.Parcelable
import com.example.capstonemobile.data.source.local.entity.Plant
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlantResponse(
    var code: String = "",
    var status: String = "",
    var data: List<Plant> = emptyList()
): Parcelable
