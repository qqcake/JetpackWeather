package com.bigcake.jetpackweather.data.mapper

import com.bigcake.jetpackweather.data.model.WeatherDto
import com.bigcake.jetpackweather.domain.model.Weather
import kotlin.math.round

fun WeatherDto.toDomain(): Weather {
    val primaryWeather = weather[0]
    return Weather(
        temperature = round(main.temp),
        minTemperature = round(main.tempMin),
        feelsLike = round(main.feelsLike),
        cityName = name,
        icon = "https://openweathermap.org/img/wn/${primaryWeather.icon}@2x.png",
        description = primaryWeather.description,
    )
}