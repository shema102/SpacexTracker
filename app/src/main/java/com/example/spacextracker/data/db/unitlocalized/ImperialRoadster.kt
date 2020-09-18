package com.example.spacextracker.data.db.unitlocalized

import androidx.room.ColumnInfo

data class ImperialRoadster (
    @ColumnInfo(name = "earthDistanceMi")
    override val earthDistance: Double,
    @ColumnInfo(name = "marsDistanceMi")
    override val marsDistance: Double,
    @ColumnInfo(name = "speedMph")
    override val speed: Double,
    @ColumnInfo(name = "launchMassLbs")
    override val launchMass: Int
): UnitSpecificRoadster