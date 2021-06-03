package com.example.capstonemobile.data.source.remote.response

import android.os.Parcelable
import com.example.capstonemobile.data.source.local.entity.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    val status: Int,
    val message: String,
    val user: User
): Parcelable

