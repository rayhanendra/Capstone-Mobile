package com.example.capstonemobile.data.source.remote.response

import android.os.Parcelable
import com.example.capstonemobile.data.source.local.entity.Token
import com.example.capstonemobile.data.source.local.entity.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataResponse(
    val token: Token = Token(),
    val user: User = User()
):Parcelable
