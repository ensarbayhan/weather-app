package com.eb.weatherapp.di

import com.eb.weatherapp.BuildConfig
import com.eb.weatherapp.apiservice.WeatherApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    fun getRetrofit(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(getGsonConverterFactory())
            .client(getOkHttpClient())
            .build()
            .create(WeatherApi::class.java)
    }

    private fun getGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
}
