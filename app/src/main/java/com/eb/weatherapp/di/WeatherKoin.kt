package com.eb.weatherapp.di

import com.eb.weatherapp.ui.MainViewModel
import com.eb.weatherapp.usecases.GetWeatherUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object WeatherAppKoin {
    val appModule = module {
        // ViewModels
        viewModel { MainViewModel(get()) }

        // Modules
        single { NetworkModule.getRetrofit() }
        single { GetWeatherUseCase(get()) }
    }
}
