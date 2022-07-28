package com.bigcake.jetpackweather.data.source

import com.bigcake.jetpackweather.domain.model.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FusedLocationDataSource @Inject constructor(
    private val locationProviderClient: FusedLocationProviderClient
) {
    fun requestLocation(): Flow<Location> = callbackFlow {
        locationProviderClient.lastLocation.addOnSuccessListener {
            trySend(Location(it.latitude, it.longitude))
            channel.close()
        }.addOnFailureListener {
            trySend(Location())
            channel.close()
        }
        awaitClose()
    }
}