package com.bigcake.jetpackweather.presentation

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bigcake.jetpackweather.common.Resource
import com.bigcake.jetpackweather.domain.usecase.GetCachedWeatherUseCase
import com.bigcake.jetpackweather.domain.usecase.GetUserLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val getCachedWeatherUseCase: GetCachedWeatherUseCase,
    private val getUserLocationUseCase: GetUserLocationUseCase
) : AndroidViewModel(application) {
    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        loadWeather()
    }

    private fun loadWeather() {
        getCachedWeatherUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let {

                    } ?: run {
                        loadLocatedWeather()
                    }
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun loadLocatedWeather() {
        if (!checkPermissionsGranted(getApplication())) {
            _state.value = MainState(requiresLocation = true)
            return
        }
        getUserLocationUseCase()
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = MainState(weather = result.data)
                        result.data?.icon?.let { Log.d("Martin", it) }
                    }
                    is Resource.Error -> TODO()
                    is Resource.Loading -> TODO()
                }
            }
            .launchIn(viewModelScope)
    }

    private fun checkPermissionsGranted(context: Context) = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ).all {
        PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(context, it)
    }

    fun onLocationPermissionResult(permissionGranted: Boolean) {
        if (permissionGranted) {
            Log.d("Martin", "location permission granted")
            loadLocatedWeather()
        } else {
            Log.d("Martin", "location permission denied")
        }
    }
}