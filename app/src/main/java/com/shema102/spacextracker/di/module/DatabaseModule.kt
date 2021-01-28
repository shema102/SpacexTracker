package com.shema102.spacextracker.di.module

import android.app.Application
import androidx.room.Room
import com.shema102.spacextracker.data.db.SpacexDatabase
import com.shema102.spacextracker.data.network.SpacexNetworkDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(application: Application) {
    private val spacexApplication = application

    private lateinit var spacexDatabase: SpacexDatabase

    @Singleton
    @Provides
    fun providesSpacexDatabase(): SpacexDatabase {
        spacexDatabase = Room.databaseBuilder(
            spacexApplication,
            SpacexDatabase::class.java,
            "launches.db"
        ).build()
        return spacexDatabase
    }

    @Singleton
    @Provides
    fun providesNextLaunchDao(spacexDatabase: SpacexDatabase) = spacexDatabase.nextLaunchDao()

    @Singleton
    @Provides
    fun providesRoadsterDao(spacexDatabase: SpacexDatabase) = spacexDatabase.roadsterDao()

    @Singleton
    @Provides
    fun providesLaunchesDao(spacexDatabase: SpacexDatabase) = spacexDatabase.launchesDao()

    @Singleton
    @Provides
    fun providesPayloadDao(spacexDatabase: SpacexDatabase) = spacexDatabase.payloadDao()

    @Singleton
    @Provides
    fun providesCrewDao(spacexDatabase: SpacexDatabase) = spacexDatabase.crewDao()

}