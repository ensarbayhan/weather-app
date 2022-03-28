package com.eb.weatherapp.usecases

import com.eb.weatherapp.apiservice.NetworkErrorCodes
import com.eb.weatherapp.apiservice.WeatherApi
import com.eb.weatherapp.common.Resource
import com.eb.weatherapp.models.WeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import java.net.ConnectException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLHandshakeException

class GetWeatherUseCase(
    private val weatherApi: WeatherApi
) : KoinComponent {
    fun getWeatherData(latitude: Double, longitude: Double): Flow<Resource<WeatherResponse>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(weatherApi.getWeatherByLocation(latitude, longitude)))
        } catch (e: ConnectException) {
            emit(Resource.Error(NetworkErrorCodes.CONNECTION_ERROR.message + " Error Message: " + e.localizedMessage))
        } catch (e: SSLHandshakeException) {
            emit(Resource.Error(NetworkErrorCodes.CONNECTION_SSL_ERROR.message + " Error Message: " + e.localizedMessage))
        } catch (e: TimeoutException) {
            emit(Resource.Error(NetworkErrorCodes.CONNECTION_TIMEOUT_ERROR.message + " Error Message: " + e.localizedMessage))
        } catch (e: Exception) {
            emit(Resource.Error(NetworkErrorCodes.CONNECTION_UNKNOWN_ERROR.message + " Error Message: " + e.localizedMessage))
        }
    }
}
