package com.shema102.spacextracker.internal

import java.text.DateFormat
import java.util.*
import kotlin.math.round

fun dateTimeFromTimestamp(timestamp: Int): String {
    val milis = timestamp.toLong() * 1000
    val dateTime = Date(milis)
    val date = DateFormat.getDateInstance(DateFormat.SHORT).format(dateTime)
    val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(dateTime)
    return "$time $date"
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}