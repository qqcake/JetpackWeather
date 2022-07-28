package com.bigcake.jetpackweather.domain.repository

import com.bigcake.jetpackweather.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getUserLocation(): Flow<Location>
}
