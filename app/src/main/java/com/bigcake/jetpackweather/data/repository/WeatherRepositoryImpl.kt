package com.bigcake.jetpackweather.data.repository

import com.bigcake.jetpackweather.common.Resource
import com.bigcake.jetpackweather.data.api.WeatherApi
import com.bigcake.jetpackweather.data.mapper.toDomain
import com.bigcake.jetpackweather.domain.model.Weather
import com.bigcake.jetpackweather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {
    override fun getWeather(latitude: Double, longitude: Double): Flow<Resource<Weather>> = flow {
        val weatherDto =
            weatherApi.getWeather(latitude, longitude)
        emit(Resource.Success(weatherDto.toDomain()))
    }
}