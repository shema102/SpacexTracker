package com.shema102.spacextracker.internal

import android.view.View
import java.text.DateFormat
import java.util.*
import kotlin.math.round

fun dateTimeFromTimestamp(timestamp: Int): String {
    val millis = timestamp.toLong() * 1000
    val dateTime = Date(millis)
    val date = DateFormat.getDateInstance(DateFormat.SHORT).format(dateTime)
    val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(dateTime)
    return "$time $date"
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

fun View.makeVisible(){
    this.visibility = View.VISIBLE
}

fun View.makeInvisible(){
    this.visibility = View.INVISIBLE
}

fun View.makeGone(){
    this.visibility = View.GONE
}