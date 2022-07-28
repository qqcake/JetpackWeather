package com.bigcake.jetpackweather.data.repository

import com.bigcake.jetpackweather.data.source.FusedLocationDataSource
import com.bigcake.jetpackweather.domain.model.Location
import com.bigcake.jetpackweather.domain.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val fusedLocationDataSource: FusedLocationDataSource
) : LocationRepository {
    override fun getUserLocation(): Flow<Location> {
        return fusedLocationDataSource.requestLocation().flowOn(Dispatchers.Default)
    }
}