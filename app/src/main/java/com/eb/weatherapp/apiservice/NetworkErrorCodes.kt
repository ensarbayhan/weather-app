package com.eb.weatherapp.apiservice

enum class NetworkErrorCodes(var message: String) {
    CONNECTION_ERROR("Please check your internet connection."),
    CONNECTION_TIMEOUT_ERROR("Connection timed out."),
    CONNECTION_SSL_ERROR("SSL connection cannot be verified."),
    CONNECTION_UNKNOWN_ERROR("Couldn't reach server. Check your internet connection."),
}
