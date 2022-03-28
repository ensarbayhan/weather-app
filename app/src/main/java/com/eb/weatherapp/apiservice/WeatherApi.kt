package com.eb.weatherapp.apiservice

import com.eb.weatherapp.BuildConfig
import com.eb.weatherapp.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApi {
    @GET("forecast/{apiKey}/{latitude},{longitude}?units=uk")
    suspend fun getWeatherByLocation(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
        @Path("apiKey") apiKey: String = BuildConfig.API_KEY
    ): WeatherResponse
}
