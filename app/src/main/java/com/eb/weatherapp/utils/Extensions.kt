package com.eb.weatherapp.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.widget.Toast


fun Activity.toast(message: String?) {
    message?.let {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

fun Activity.openSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", packageName, null)
    }
    startActivity(intent)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Double?.withDegree(): String {
    return this?.toInt().toString() + "°"
}

fun Double.toPercentage(): String {
    val value = this * 100
    return value.toInt().toString() + "%"
}
