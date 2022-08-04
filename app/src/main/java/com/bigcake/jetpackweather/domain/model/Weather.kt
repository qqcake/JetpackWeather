package com.bigcake.jetpackweather.domain.model

data class Weather(
    val isUserLocation: Boolean = false,
    val cityName: String? = null,
    val temperature: Int,
    val maxTemperature: Int,
    val minTemperature: Int,
    val feelsLike: Int,
    val icon: String? = null,
    val description: String? = null,
)
