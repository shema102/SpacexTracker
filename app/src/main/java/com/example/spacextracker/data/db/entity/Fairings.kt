package com.example.spacextracker.data.db.entity

import com.google.gson.annotations.SerializedName

data class Fairings(
    @SerializedName("reused")
    val reused: Boolean? = null,
    @SerializedName("recovery_attempt")
    val recoveryAttempt: Boolean? = null,
    @SerializedName("recovered")
    val recovered: Boolean? = null,
    @SerializedName("ships")
    val ships: List<String>
)
