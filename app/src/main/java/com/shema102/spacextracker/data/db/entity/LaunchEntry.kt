package com.shema102.spacextracker.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shema102.spacextracker.data.db.entity.converters.CrewListConverter
import com.shema102.spacextracker.data.db.entity.converters.IdListConverter
import com.shema102.spacextracker.data.db.entity.converters.PayloadListConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "launch_entry")
@TypeConverters(
    IdListConverter::class,
    PayloadListConverter::class,
    CrewListConverter::class
)
data class LaunchEntry(
    @SerializedName("crew")
    val crewId: List<String>,
    var crewList: List<Crew>,
    @SerializedName("date_local")
    val dateLocal: String,
    @SerializedName("date_precision")
    val datePrecision: String,
    @SerializedName("date_unix")
    val dateUnix: Int,
    @SerializedName("details")
    val details: String?,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: String,
    @SerializedName("launchpad")
    val launchpad: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("payloads")
    val payloadsId: List<String>,
    var payloadsList: List<Payload>,
    @SerializedName("rocket")
    val rocket: String?,
    @SerializedName("static_fire_date_unix")
    val staticFireDateUnix: Int?,
    @SerializedName("tbd")
    val tbd: Boolean,
    @SerializedName("upcoming")
    val upcoming: Boolean,
    @SerializedName("window")
    val window: Int,
    @SerializedName("links")
    @Embedded(prefix = "links_")
    val links: Links?,
) {
//    @PrimaryKey(autoGenerate = true)
//    var key: Int = 0
}