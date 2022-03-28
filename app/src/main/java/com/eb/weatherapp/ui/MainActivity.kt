package com.eb.weatherapp.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.eb.weatherapp.R
import com.eb.weatherapp.databinding.ActivityMainBinding
import com.eb.weatherapp.models.WeatherResponse
import com.eb.weatherapp.ui.adapters.DailyAdapter
import com.eb.weatherapp.ui.adapters.HourlyAdapter
import com.eb.weatherapp.utils.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModel<MainViewModel>()

    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var areaName: String = ""

    private val locationPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
                getWeatherData()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        mainViewModel.weatherData.observe(this) {
            if (it.isLoading) {
                if (!binding.swipeContainer.isRefreshing) {
                    binding.progressBar.visible()
                }
            } else {
                binding.progressBar.gone()
            }

            if (it.error.isNotEmpty()) {
                toast(it.error)
            }

            if (it.weather != null) {
                initViews(it.weather)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getWeatherData()
    }

    private fun initViews(weather: WeatherResponse) {
        if (weather.currentData == null || weather.daily == null || weather.hourly == null) {
            toast("Unable to get weather data. Please try again later.")
            return
        }

        binding.swipeContainer.setOnRefreshListener {
            getWeatherData()
        }

        val current = weather.currentData!!
        val daily = weather.daily!!
        val hourly = weather.hourly!!
        val today = daily.data[0]

        binding.title.text = current.summary
        binding.areaName.text = areaName
        binding.currentDegree.text = current.temperature.withDegree()
        binding.date.text = current.time?.toDateString()
        current.icon?.let { icon ->
            binding.currentIcon.setImageResource(icon.icon)
        }

        binding.currentMinHigh.text = getString(
            R.string.min_high_degree,
            today.temperatureMax.withDegree(),
            today.temperatureMin.withDegree(),
            current.apparentTemperature.withDegree()
        )
        binding.dailyRecyclerView.visible()
        binding.dailyRecyclerView.adapter = DailyAdapter(daily.data)
        binding.hourlyRecyclerView.visible()
        binding.hourlyRecyclerView.adapter = HourlyAdapter(hourly.data)

        binding.swipeContainer.isRefreshing = false
    }

    private fun getWeatherData() {
        LocationUtils.getCurrentLocation(
            activity = this,
            fusedLocationClient,
            locationPermissionRequest,
            onLocationRetrieved = { latitude, longitude, areaName ->
                this.areaName = areaName
                mainViewModel.getWeatherData(latitude, longitude)
            }
        )
    }
}
