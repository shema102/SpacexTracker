package com.example.spacextracker.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "launch_entry")
data class Launch(
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("date_unix")
    val dateUnix: String,
    @SerializedName("date_local")
    val dateLocal: String,
    @SerializedName("date_precision")
    val datePrecision: String, // "enum": [ "half","quarter", "year", "month","day","hour"]
    @SerializedName("tdb")
    val tdb: Boolean = false,
    @SerializedName("net")
    val net: Boolean = false,
    @SerializedName("window")
    val window: Int? = null,
    @SerializedName("rocket")
    val rocket: String? = null,
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("failures")
    val failures: List<Failure>,
    @SerializedName("upcoming")
    val upcoming: Boolean = true,
    @SerializedName("details")
    val details: String? = null,
    @SerializedName("fairings")
    val fairings: Fairings,
    @SerializedName("crew")
    val crew: List<String>,
    @SerializedName("ships")
    val ships: List<String>,
    @SerializedName("capsules")
    val capsules: List<String>,
    @SerializedName("payloads")
    val payloads: List<String>,
    @SerializedName("launchpad")
    val launchpad: String? = null,
    @SerializedName("cores")
    val cores: List<Core>,

    @SerializedName("links")
    @Embedded(prefix = "link_")
    val links: Links,
)