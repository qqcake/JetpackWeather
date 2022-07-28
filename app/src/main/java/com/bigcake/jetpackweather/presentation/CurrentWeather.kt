package com.bigcake.jetpackweather.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CurrentWeather(
    state: MainState,
) {
    state.weather?.let { weather ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = weather.icon,
                    contentDescription = weather.description,
                    modifier = Modifier
                        .size(48.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = weather.description ?: "")
            }
            Text(text = "${weather.temperature} °C", fontSize = 50.sp)
            Text(text = "${weather.feelsLike} ${weather.minTemperature}")
        }
    }
}