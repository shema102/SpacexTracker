package com.shema102.spacextracker.data.db.unitlocalized

import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import com.shema102.spacextracker.data.db.entity.converters.StringListConverter

@TypeConverters(StringListConverter::class)
data class MetricRoadster (
    @ColumnInfo(name = "earthDistanceKm")
    override val earthDistance: Double,
    @ColumnInfo(name = "marsDistanceKm")
    override val marsDistance: Double,
    @ColumnInfo(name = "speedKph")
    override val speed: Double,
    @ColumnInfo(name = "launchMassKg")
    override val launchMass: Int,
    override val details: String,
    override val name: String,
    override val noradId: Int,
    override val orbitType: String,
    override val periodDays: Double,
    override val video: String,
    override val wikipedia: String,
    override val images: List<String>

): UnitSpecificRoadster