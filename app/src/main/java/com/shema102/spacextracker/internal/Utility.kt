package com.shema102.spacextracker.internal

import java.text.DateFormat
import java.util.*

fun dateTimeFromTimestamp(timestamp: Int): String {
    val milis = timestamp.toLong() * 1000
    val dateTime = Date(milis)
    val date = DateFormat.getDateInstance(DateFormat.SHORT).format(dateTime)
    val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(dateTime)
    return "$time $date"
}