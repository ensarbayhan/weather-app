package com.eb.weatherapp.ui

import com.eb.weatherapp.models.WeatherResponse

data class WeatherState(
    val isLoading: Boolean = false,
    val weather: WeatherResponse? = null,
    val error: String = ""
)
