package com.eb.weatherapp.models

import com.google.gson.annotations.SerializedName


data class CurrentData(
    @SerializedName("time")
    var time: Int? = null,

    @SerializedName("summary")
    var summary: String? = null,

    @SerializedName("icon")
    var icon: WeatherIcon? = null,

    @SerializedName("precipIntensity")
    var precipIntensity: Double? = null,

    @SerializedName("precipProbability")
    var precipProbability: Double? = null,

    @SerializedName("temperature")
    var temperature: Double? = null,

    @SerializedName("apparentTemperature")
    var apparentTemperature: Double? = null,

    @SerializedName("dewPoint")
    var dewPoint: Double? = null,

    @SerializedName("humidity")
    var humidity: Double? = null,

    @SerializedName("pressure")
    var pressure: Double? = null,

    @SerializedName("windSpeed")
    var windSpeed: Double? = null,

    @SerializedName("windGust")
    var windGust: Double? = null,

    @SerializedName("windBearing")
    var windBearing: Double? = null,

    @SerializedName("cloudCover")
    var cloudCover: Double? = null,

    @SerializedName("uvIndex")
    var uvIndex: Double? = null,

    @SerializedName("visibility")
    var visibility: Double? = null,

    @SerializedName("ozone")
    var ozone: Double? = null
)
