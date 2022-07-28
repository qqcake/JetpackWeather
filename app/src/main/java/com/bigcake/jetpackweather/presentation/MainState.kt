package com.bigcake.jetpackweather.presentation

import com.bigcake.jetpackweather.domain.model.Weather

data class MainState(
    val requiresLocation: Boolean = false,
    val weather: Weather? = null
)
