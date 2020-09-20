package com.example.spacextracker.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "launch_entry")
@TypeConverters(IdListConverter::class)
data class LaunchEntry(
    @SerializedName("crew")
    val crew: List<String>,
    @SerializedName("date_local")
    val dateLocal: String,
    @SerializedName("date_precision")
    val datePrecision: String,
    @SerializedName("date_unix")
    val dateUnix: Int,
    @SerializedName("details")
    val details: String?,
    @SerializedName("id")
    val id: String,
    @SerializedName("launchpad")
    val launchpad: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("payloads")
    val payloads: List<String>?,
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
){
    @PrimaryKey(autoGenerate = true)
    var key: Int = 0
}