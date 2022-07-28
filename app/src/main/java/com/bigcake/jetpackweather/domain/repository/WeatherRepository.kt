package com.bigcake.jetpackweather.domain.repository

import com.bigcake.jetpackweather.common.Resource
import com.bigcake.jetpackweather.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(latitude: Double, longitude: Double): Flow<Resource<Weather>>
}