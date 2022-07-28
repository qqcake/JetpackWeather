package com.bigcake.jetpackweather.domain.usecase

import com.bigcake.jetpackweather.common.Resource
import com.bigcake.jetpackweather.domain.model.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCachedWeatherUseCase @Inject constructor() {
    operator fun invoke(): Flow<Resource<Weather?>> = flow {
        // TODO: return cached weather from weather cache
        emit(Resource.Success(null))
    }
}