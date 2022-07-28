package com.bigcake.jetpackweather.domain.usecase

import android.util.Log
import com.bigcake.jetpackweather.common.Resource
import com.bigcake.jetpackweather.domain.repository.LocationRepository
import com.bigcake.jetpackweather.domain.model.Location
import com.bigcake.jetpackweather.domain.model.Weather
import com.bigcake.jetpackweather.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetUserLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository
) {
    @OptIn(FlowPreview::class)
    operator fun invoke(): Flow<Resource<Weather>> =
        locationRepository.getUserLocation().flatMapMerge {
            if (!it.isValid()) {
                Log.d("Martin", "got invalid location")
                emptyFlow()
            } else {
                Log.d("Martin", "location: ${it.latitude}, ${it.longitude}")
                weatherRepository.getWeather(it.latitude, it.longitude)
            }
        }.flowOn(Dispatchers.IO)
}