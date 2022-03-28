package com.eb.weatherapp.utils

import android.text.format.DateFormat
import java.util.*

fun Int.toDateString(): String {
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.timeInMillis = this * 1000L
    return DateFormat.format("EEEE, hh:mm a", calendar).toString()
}

fun Int.toDayString(): String {
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.timeInMillis = this * 1000L
    if (calendar.isToday()) {
        return "Today"
    }
    return DateFormat.format("EEEE", calendar).toString()
}

fun Int.toHourString(): String {
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.timeInMillis = this * 1000L
    return DateFormat.format("hh a", calendar).toString()
}

fun Calendar.isToday(): Boolean {
    val date = Calendar.getInstance()
    return date[Calendar.YEAR] == get(Calendar.YEAR) && date[Calendar.DAY_OF_YEAR] == get(Calendar.DAY_OF_YEAR)
}
