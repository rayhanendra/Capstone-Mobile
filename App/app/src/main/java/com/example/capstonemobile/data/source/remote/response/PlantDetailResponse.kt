package com.example.capstonemobile.data.source.remote.response

import android.os.Parcelable
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PlantDetailResponse (
        var code : String = "",
        var status: String = "",
        var data: List<PlantDetail> = emptyList()
): Parcelable