package com.example.capstonemobile.data.source.remote.response

import android.os.Parcelable
import com.example.capstonemobile.data.source.local.entity.CheckupEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CheckupByUserResponse (
    var code: String = "",
    var status: String = "",
    var data:  List<CheckupEntity>
) : Parcelable