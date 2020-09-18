package com.example.spacextracker.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "payload")
@TypeConverters(IdListConverter::class)
data class Payload(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("reused")
    val reused: Boolean = false,
    @SerializedName("name")
    val launch: String? = null,
    @SerializedName("customers")
    val customers: List<String>? = null,
    @SerializedName("nationalities")
    val nationalities: List<String>? = null,
    @SerializedName("manufacturers")
    val manufacturers: List<String>? = null,
    @SerializedName("mass_kg")
    val mass_kg: Int? = null,
    @SerializedName("mass_lbs")
    val mass_lbs: Int? = null,
    @SerializedName("orbit")
    val orbit: String? = null,
    @SerializedName("id")
    val id: String,
){
    @PrimaryKey(autoGenerate = true)
    var key: Int = 0
}