package com.bigcake.jetpackweather.domain.model

data class Weather(
    val isUserLocation: Boolean = false,
    val cityName: String? = null,
    val temperature: Double,
    val minTemperature: Double,
    val feelsLike: Double,
    val icon: String? = null,
    val description: String? = null,
)
