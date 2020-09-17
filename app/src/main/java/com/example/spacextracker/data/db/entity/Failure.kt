package com.example.spacextracker.data.db.entity

import com.google.gson.annotations.SerializedName

data class Failure(
    @SerializedName("time")
    val time: Int,
    @SerializedName("altitude")
    val altitude: Int,
    @SerializedName("reason")
    val reason: String
)