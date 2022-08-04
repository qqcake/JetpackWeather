package com.bigcake.jetpackweather.data.mapper

import com.bigcake.jetpackweather.data.model.WeatherDto
import com.bigcake.jetpackweather.domain.model.Weather

fun WeatherDto.toDomain(): Weather {
    val primaryWeather = weather[0]
    return Weather(
        temperature = main.temp.toInt(),
        minTemperature = main.tempMin.toInt(),
        maxTemperature = main.tempMax.toInt(),
        feelsLike = main.feelsLike.toInt(),
        cityName = name,
        icon = "https://openweathermap.org/img/wn/${primaryWeather.icon}@2x.png",
        description = primaryWeather.description,
    )
}