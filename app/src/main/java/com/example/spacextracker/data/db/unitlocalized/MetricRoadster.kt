package com.example.spacextracker.data.db.unitlocalized

import androidx.room.ColumnInfo

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
    override val wikipedia: String
): UnitSpecificRoadster