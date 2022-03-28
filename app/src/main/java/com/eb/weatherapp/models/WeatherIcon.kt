package com.eb.weatherapp.models

import androidx.annotation.DrawableRes
import com.eb.weatherapp.R
import com.google.gson.annotations.SerializedName

enum class WeatherIcon(@DrawableRes var icon: Int) {
    @SerializedName("clear-day")
    CLEAR_DAY(R.drawable.ic_clear_day),

    @SerializedName("clear-night")
    CLEAR_NIGHT(R.drawable.ic_clear_night),

    @SerializedName("partly-cloudy-day")
    PARTLY_CLOUDY_DAY(R.drawable.ic_partly_cloudy_day),

    @SerializedName("partly-cloudy-night")
    PARTLY_CLOUDY_NIGHT(R.drawable.ic_partly_cloudy_night),

    @SerializedName("cloudy")
    CLOUDY(R.drawable.ic_cloudy),

    @SerializedName("rain")
    RAIN(R.drawable.ic_rain),

    @SerializedName("sleet")
    SLEET(R.drawable.ic_sleet),

    @SerializedName("snow")
    SNOW(R.drawable.ic_snow),

    @SerializedName("wind")
    WIND(R.drawable.ic_windy),

    @SerializedName("fog")
    FOG(R.drawable.ic_fog)
}
