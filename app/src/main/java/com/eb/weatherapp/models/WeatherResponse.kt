package com.eb.weatherapp.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("latitude")
    var latitude: Double? = null,

    @SerializedName("longitude")
    var longitude: Double? = null,

    @SerializedName("timezone")
    var timezone: String? = null,

    @SerializedName("currently")
    var currentData: CurrentData? = CurrentData(),

    @SerializedName("hourly")
    var hourly: Hourly? = Hourly(),

    @SerializedName("daily")
    var daily: Daily? = Daily(),

    @SerializedName("flags")
    var flags: Flags = Flags(),

    @SerializedName("offset")
    var offset: Int? = null
) : BaseResponse()
