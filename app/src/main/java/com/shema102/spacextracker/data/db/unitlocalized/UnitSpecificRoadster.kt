package com.shema102.spacextracker.data.db.unitlocalized

interface UnitSpecificRoadster {
    val name: String
    val details: String
    val earthDistance: Double
    val marsDistance: Double
    val speed: Double
    val launchMass: Int
    val noradId: Int
    val orbitType: String
    val periodDays: Double
    val video: String
    val wikipedia: String
    val images: List<String>
}