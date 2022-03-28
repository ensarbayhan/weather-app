package com.eb.weatherapp

import android.app.Application
import com.eb.weatherapp.di.WeatherAppKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start dependency injection
        startKoin {
            androidContext(this@WeatherApp)
            modules(WeatherAppKoin.appModule)
        }
    }
}
