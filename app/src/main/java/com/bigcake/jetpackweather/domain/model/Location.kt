package com.bigcake.jetpackweather.domain.model

data class Location(
    val latitude: Double = Double.NaN,
    val longitude: Double = Double.NaN
) {
    fun isValid() = !latitude.isNaN() && !longitude.isNaN()
}
