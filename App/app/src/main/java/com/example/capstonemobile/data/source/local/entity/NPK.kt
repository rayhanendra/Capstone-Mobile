package com.example.capstonemobile.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "npks")
data class NPK(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = "",

    @ColumnInfo(name = "humidity")
    var humidity: Double = 0.0,

    @ColumnInfo(name = "temperature")
    var temperature: Double = 0.0,

    @ColumnInfo(name = "nitrogen")
    var nitrogen: Double = 0.0,

    @ColumnInfo(name = "phosporus")
    var phosporus: Double = 0.0,

    @ColumnInfo(name = "potassium")
    var potassium: Double = 0.0
)
