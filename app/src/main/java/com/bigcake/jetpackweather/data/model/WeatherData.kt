package com.bigcake.jetpackweather.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val lat: Double,
    val lon: Double,
    @ColumnInfo(name = "is_user_location") val isUserLocation: Boolean
)