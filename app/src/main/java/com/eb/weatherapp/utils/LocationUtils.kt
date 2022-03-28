package com.eb.weatherapp.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import com.eb.weatherapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.OnTokenCanceledListener
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object LocationUtils : KoinComponent {

    private val context by inject<Context>()

    private var isPermissionRequested = false

    private val cancellationToken = object : CancellationToken() {
        override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken {
            return this
        }

        override fun isCancellationRequested(): Boolean {
            return false
        }
    }

    private fun isLocationPermissionGranted(activity: Activity): Boolean {
        return ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun askForLocationPermission(
        activity: Activity,
        locationPermissionRequest: ActivityResultLauncher<Array<String>>
    ) {
        if (!isPermissionRequested) {
            locationPermissionRequest.launch(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            )
            isPermissionRequested = true
        } else {
            DialogUtils.showAlertDialog(
                context = activity,
                title = activity.getString(R.string.warning),
                message = activity.getString(R.string.location_permission_text),
                positiveButtonText = activity.getString(R.string.open_settings),
                onPositiveButtonClicked = {
                    activity.openSettings()
                }
            )
        }
    }

    private fun findAreaName(latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(context)
        geocoder.getFromLocation(latitude, longitude, 1)?.let { addressList ->
            if (addressList.isNotEmpty()) {
                val address = addressList[0]
                address.subAdminArea?.let {
                    return it
                } ?: return address.adminArea
            }
        }
        return ""
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(
        activity: Activity,
        fusedLocationClient: FusedLocationProviderClient,
        locationPermissionRequest: ActivityResultLauncher<Array<String>>,
        onLocationRetrieved: (latitude: Double, longitude: Double, area: String) -> Unit
    ) {
        if (isLocationPermissionGranted(activity)) {
            fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, cancellationToken)
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        val areaName = findAreaName(it.latitude, it.longitude)
                        onLocationRetrieved(it.latitude, it.longitude, areaName)
                    } ?: run {
                        activity.toast("Unable to get your location. Please try again later.")
                    }
                }
        } else {
            askForLocationPermission(activity, locationPermissionRequest)
        }

    }

}
