package com.example.spacextracker.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val ROADSTER_ID = 0

@Entity(tableName = "roadster")
data class Roadster(
    @SerializedName("name")
    val name: String,
    @SerializedName("launch_date_unix")
    val launchDataUnix: Int,
    @SerializedName("launch_mass_kg")
    val launchMassKg: Int,
    @SerializedName("norad_id")
    val noradId: Int,
    @SerializedName("speed_kph")
    val speedKph: Int,
    @SerializedName("earth_distance_km")
    val earthDistanceKm: Int,
    @SerializedName("mars_distance_km")
    val marsDistanceKm: Int,
    @SerializedName("details")
    val details: String,
) {
    @PrimaryKey(autoGenerate = false)
    var key: Int = ROADSTER_ID
}