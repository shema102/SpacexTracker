package com.example.spacextracker.model

import com.google.gson.annotations.SerializedName

data class UUID(
    val value: String
)

data class Capsule(
    @SerializedName("serial")
    val serial: String,
    @SerializedName("status")
    val status: String, // "enum": ["unknown", "active", "retired", "destroyed"]
    @SerializedName("dragon")
    val dragon: String? = null,
    @SerializedName("reuse_count")
    val reuseCount: Int = 0,
    @SerializedName("water_landings")
    val waterLandings: Int = 0,
    @SerializedName("land_landings")
    val landLandings: Int = 0,
    @SerializedName("last_update")
    val lastUpdate: String? = null,
    @SerializedName("launches")
    val launches: List<String?>? = null,
)

data class Launch(
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("date_utc")
    val dateUtc: String,
    @SerializedName("date_unix")
    val dateUnix: String,
    @SerializedName("date_local")
    val dateLocal: String,
    @SerializedName("date_precision")
    val datePrecision: String, // "enum": [ "half","quarter", "year", "month","day","hour"]
    @SerializedName("static_fire_date_utc")
    val staticFireDateUtc: String? = null,
    @SerializedName("static_fire_date_unix")
    val staticFireDateUnix: String? = null,
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
    val links: Links,
    @SerializedName("auto_update")
    val autoUpdate: Boolean = true
)

data class Failure(
    @SerializedName("time")
    val time: Int,
    @SerializedName("altitude")
    val altitude: Int,
    @SerializedName("reason")
    val reason: String
)

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

data class Links(
    @SerializedName("parch")
    val patch: Patch,
    @SerializedName("reddit")
    val reddit: Reddit,
    @SerializedName("flickr")
    val flickr: Flickr,
    @SerializedName("presskit")
    val presskit: String? = null,
    @SerializedName("webcast")
    val webcast: String? = null,
    @SerializedName("youtube_id")
    val youtubeId: String? = null,
    @SerializedName("article")
    val article: String? = null,
    @SerializedName("wikipedia")
    val wikipedia: String? = null,
) {
    data class Patch(
        @SerializedName("small")
        val small: String? = null,
        @SerializedName("large")
        val large: String? = null
    )

    data class Reddit(
        @SerializedName("campaign")
        val campaign: String? = null,
        @SerializedName("launch")
        val launch: String? = null,
        @SerializedName("media")
        val media: String? = null,
        @SerializedName("recovery")
        val recovery: String? = null
    )

    data class Flickr(
        @SerializedName("small")
        val small: List<String>,
        @SerializedName("original")
        val original: List<String>
    )
}
