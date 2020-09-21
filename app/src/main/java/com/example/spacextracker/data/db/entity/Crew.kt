package com.example.spacextracker.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.spacextracker.data.db.entity.converters.IdListConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "crew")
@TypeConverters(IdListConverter::class)
data class Crew(
    @SerializedName("agency")
    val agency: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("launches")
    val launches: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("wikipedia")
    val wikipedia: String
) {
    @PrimaryKey(autoGenerate = true)
    var key: Int = 0
}