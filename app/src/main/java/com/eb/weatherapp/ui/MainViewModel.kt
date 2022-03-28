package com.eb.weatherapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eb.weatherapp.apiservice.NetworkErrorCodes
import com.eb.weatherapp.common.Resource
import com.eb.weatherapp.models.WeatherResponse
import com.eb.weatherapp.usecases.GetWeatherUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(
    private var getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherState>().apply { value = WeatherState() }
    val weatherData: LiveData<WeatherState> = _weatherData

    fun getWeatherData(latitude: Double, longitude: Double) {
        getWeatherUseCase.getWeatherData(latitude, longitude).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _weatherData.value = WeatherState(weather = result.data ?: WeatherResponse())
                }
                is Resource.Error -> {
                    _weatherData.value = WeatherState(
                        error = result.message ?: NetworkErrorCodes.CONNECTION_UNKNOWN_ERROR.message
                    )
                }
                is Resource.Loading -> {
                    _weatherData.value = WeatherState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
