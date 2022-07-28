package com.bigcake.jetpackweather.di.module

import com.bigcake.jetpackweather.data.repository.LocationRepositoryImpl
import com.bigcake.jetpackweather.data.repository.WeatherRepositoryImpl
import com.bigcake.jetpackweather.domain.repository.LocationRepository
import com.bigcake.jetpackweather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindLocationRepo(impl: LocationRepositoryImpl): LocationRepository

    @Binds
    abstract fun bindWeatherRepo(impl: WeatherRepositoryImpl): WeatherRepository
}