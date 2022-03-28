package com.eb.weatherapp.models

import com.google.gson.annotations.SerializedName


data class Flags(
    @SerializedName("sources")
    var sources: List<String> = listOf(),

    @SerializedName("meteoalarm-license")
    var meteoalarmLicense: String? = null,

    @SerializedName("nearest-station")
    var nearestStation: Double? = null,

    @SerializedName("units")
    var units: String? = null
)
