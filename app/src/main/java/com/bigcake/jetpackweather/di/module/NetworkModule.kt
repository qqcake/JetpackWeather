package com.bigcake.jetpackweather.di.module

import com.bigcake.jetpackweather.BuildConfig
import com.bigcake.jetpackweather.data.api.WeatherApi
import com.bigcake.jetpackweather.di.qualifier.QueryParameterInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        private const val APP_ID = "appid"
        private const val UNITS = "units"
        private const val UNITS_METRIC = "metric"
    }

    @QueryParameterInterceptor
    @Provides
    fun provideQueryParamInterceptor() = Interceptor { chain ->
        var request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(APP_ID, BuildConfig.API_KEY)
            .addQueryParameter(UNITS, UNITS_METRIC)
            .build()
        request = request.newBuilder().url(url).build()
        chain.proceed(request)
    }

    @Singleton
    @Provides
    fun provideHttpClient(
        @QueryParameterInterceptor interceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Singleton
    @Provides
    fun provideJsonConverter(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi =
        retrofit.create(WeatherApi::class.java)
}