package com.shema102.spacextracker.data.db.unitlocalized

import androidx.room.ColumnInfo

data class ImperialRoadster (
    @ColumnInfo(name = "earthDistanceMi")
    override val earthDistance: Double,
    @ColumnInfo(name = "marsDistanceMi")
    override val marsDistance: Double,
    @ColumnInfo(name = "speedMph")
    override val speed: Double,
    @ColumnInfo(name = "launchMassLbs")
    override val launchMass: Int,
    override val details: String,
    override val name: String,
    override val noradId: Int,
    override val orbitType: String,
    override val periodDays: Double,
    override val video: String,
    override val wikipedia: String,
//    override val images: List<String>,

): UnitSpecificRoadster