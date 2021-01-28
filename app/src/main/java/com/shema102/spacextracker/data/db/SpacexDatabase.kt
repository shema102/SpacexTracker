package com.shema102.spacextracker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shema102.spacextracker.data.db.dao.*
import com.shema102.spacextracker.data.db.entity.*

@Database(
    entities = [NextLaunchEntry::class,
        LaunchEntry::class,
        RoadsterEntry::class,
        Crew::class,
        Payload::class,
        Links::class],
    version = 1
)

abstract class SpacexDatabase : RoomDatabase() {
    abstract fun nextLaunchDao(): NextLaunchDao
    abstract fun roadsterDao(): RoadsterDao
    abstract fun launchesDao(): LaunchesDao
    abstract fun payloadDao(): PayloadDao
    abstract fun crewDao(): CrewDao
}