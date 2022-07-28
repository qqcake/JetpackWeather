package com.bigcake.jetpackweather.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import javax.inject.Inject

class PermissionHelper @Inject constructor() {
    fun isLocationPermissionGranted(context: Context) =
        PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) || PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        )

    fun requestLocationPermission(activity: Activity) {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE_LOCATION_PERMISSION)
    }

    companion object {
        private const val REQUEST_CODE_LOCATION_PERMISSION = 1000
    }
}