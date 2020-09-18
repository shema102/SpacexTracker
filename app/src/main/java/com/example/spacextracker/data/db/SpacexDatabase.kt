package com.example.spacextracker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.spacextracker.data.db.entity.*

@Database(
    entities = [NextLaunch::class,
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

    companion object {
        @Volatile
        private var instance: SpacexDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SpacexDatabase::class.java, "launches.db"
            )
                .build()
    }
}