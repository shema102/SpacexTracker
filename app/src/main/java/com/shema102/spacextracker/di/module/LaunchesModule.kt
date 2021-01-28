package com.shema102.spacextracker.di.module

import com.shema102.spacextracker.data.db.dao.LaunchesDao
import com.shema102.spacextracker.data.db.dao.NextLaunchDao
import com.shema102.spacextracker.data.db.dao.RoadsterDao
import com.shema102.spacextracker.data.network.SpacexNetworkDataSource
import com.shema102.spacextracker.data.repository.SpacexRepository
import com.shema102.spacextracker.data.repository.SpacexRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LaunchesModule {
    @Singleton
    @Provides
    fun providesSpacexRepository(
        roadsterDao: RoadsterDao,
        nextLaunchDao: NextLaunchDao,
        launchesDao: LaunchesDao,
        launchesNetworkDataSource: SpacexNetworkDataSource
    ): SpacexRepository =
        SpacexRepositoryImpl(roadsterDao, nextLaunchDao, launchesDao, launchesNetworkDataSource)

}