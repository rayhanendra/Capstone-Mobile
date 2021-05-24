package com.example.capstonemobile.data.source.remote.response

import android.os.Parcelable
import com.example.capstonemobile.data.source.local.entity.Disease
import com.example.capstonemobile.data.source.local.entity.Plant
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DiseaseResponse (
        var code: String = "",
        var status: String = "",
        var data: Disease 
):Parcelable