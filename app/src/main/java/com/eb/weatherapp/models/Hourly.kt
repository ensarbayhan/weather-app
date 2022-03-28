package com.eb.weatherapp.models

import com.google.gson.annotations.SerializedName


data class Hourly(
    @SerializedName("summary")
    var summary: String? = null,

    @SerializedName("icon")
    var icon: WeatherIcon? = null,

    @SerializedName("data")
    var data: List<CurrentData> = listOf()
)
