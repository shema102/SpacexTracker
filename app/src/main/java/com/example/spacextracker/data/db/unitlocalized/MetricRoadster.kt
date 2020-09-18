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
    override val launchMass: Int
): UnitSpecificRoadster