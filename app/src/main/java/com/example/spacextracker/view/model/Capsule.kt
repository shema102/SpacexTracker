package com.example.spacextracker.view.model

import com.google.gson.annotations.SerializedName

data class Capsule(
	@SerializedName("serial")
	val serial: String,
	@SerializedName("status")
	val status: String, // "enum": ["unknown", "active", "retired", "destroyed"]
	@SerializedName("dragon")
	val dragon: UUID? = null,
	@SerializedName("reuse_count")
	val reuseCount: Int = 0,
	@SerializedName("water_landings")
	val waterLandings: Int = 0,
	@SerializedName("land_landings")
	val landLandings: Int = 0,
	@SerializedName("last_update")
	val lastUpdate: String? = null,
	@SerializedName("launches")
	val launches: List<UUID?>? = null,
)

data class UUID (
	val value: String
)
