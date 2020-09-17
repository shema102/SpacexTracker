package com.example.spacextracker.data.db.entity

import com.google.gson.annotations.SerializedName

data class Core(
    @SerializedName("core")
    val core: String? = null,
    @SerializedName("flight")
    val flight: Int? = null,
    @SerializedName("gridfins")
    val gridfins: Boolean? = null,
    @SerializedName("legs")
    val legs: Boolean? = null,
    @SerializedName("reused")
    val reused: Boolean? = null,
    @SerializedName("landing_attempt")
    val LandingAttempt: Boolean? = null,
    @SerializedName("landing_success")
    val landingSuccess: Boolean? = null,
    @SerializedName("landing_type")
    val landingType: String? = null,
    @SerializedName("landpad")
    val landpad: String? = null,
)