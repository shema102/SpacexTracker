package com.shema102.spacextracker.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.shema102.spacextracker.data.db.entity.converters.StringListConverter

const val ROADSTER_ID = 0

@Entity(tableName = "roadster")
@TypeConverters(StringListConverter::class)
data class RoadsterEntry(
    @SerializedName("details")
    val details: String,
    @SerializedName("earth_distance_km")
    val earthDistanceKm: Double,
    @SerializedName("earth_distance_mi")
    val earthDistanceMi: Double,
    @SerializedName("id")
    val id: String,
    @SerializedName("launch_date_unix")
    val launchDateUnix: Int,
    @SerializedName("launch_mass_kg")
    val launchMassKg: Int,
    @SerializedName("launch_mass_lbs")
    val launchMassLbs: Int,
    @SerializedName("mars_distance_km")
    val marsDistanceKm: Double,
    @SerializedName("mars_distance_mi")
    val marsDistanceMi: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("norad_id")
    val noradId: Int,
    @SerializedName("orbit_type")
    val orbitType: String,
    @SerializedName("period_days")
    val periodDays: Double,
    @SerializedName("speed_kph")
    val speedKph: Double,
    @SerializedName("speed_mph")
    val speedMph: Double,
    @SerializedName("video")
    val video: String,
    @SerializedName("wikipedia")
    val wikipedia: String,
    @SerializedName("flickr_images")
    val images: List<String>,
) {
    @PrimaryKey(autoGenerate = false)
    var key: Int = ROADSTER_ID
}