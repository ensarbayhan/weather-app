package com.eb.weatherapp.models

import com.google.gson.annotations.SerializedName


data class Daily(
    @SerializedName("summary")
    var summary: String? = null,

    @SerializedName("icon")
    var icon: WeatherIcon? = null,

    @SerializedName("data")
    var data: List<Data> = listOf()
)
