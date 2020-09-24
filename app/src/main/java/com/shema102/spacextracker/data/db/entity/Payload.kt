package com.shema102.spacextracker.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shema102.spacextracker.data.db.entity.converters.IdListConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "payload")
@TypeConverters(IdListConverter::class)
data class Payload(
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("reused")
    val reused: Boolean,
    @SerializedName("launch")
    val launch: String?,
    @SerializedName("customers")
    val customers: List<String>?,
    @SerializedName("nationalities")
    val nationalities: List<String>?,
    @SerializedName("manufacturers")
    val manufacturers: List<String>?,
    @SerializedName("mass_kg")
    val mass_kg: Double?,
    @SerializedName("mass_lbs")
    val mass_lbs: Double?,
    @SerializedName("orbit")
    val orbit: String?,
    @SerializedName("id")
    val id: String,
){
    @PrimaryKey(autoGenerate = true)
    var key: Int = 0
}