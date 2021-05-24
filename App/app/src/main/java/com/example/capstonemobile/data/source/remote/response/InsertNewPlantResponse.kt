package com.example.capstonemobile.data.source.remote.response

import android.os.Parcelable
import com.example.capstonemobile.data.source.local.entity.Disease
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InsertNewPlantResponse(
        var code: String = "",
        var status: String = "",
        var data: PlantDetail
): Parcelable