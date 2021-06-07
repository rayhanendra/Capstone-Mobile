package com.example.capstonemobile.data.source.remote.response

import com.example.capstonemobile.data.source.local.entity.UploadImage


data class CameraResponse (
    var code : String = "",
    var status: String = "",
    var data: UploadImage = UploadImage()
)