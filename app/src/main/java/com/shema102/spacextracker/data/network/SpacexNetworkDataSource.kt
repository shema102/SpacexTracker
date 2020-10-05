package com.shema102.spacextracker.data.network

import androidx.lifecycle.LiveData
import com.shema102.spacextracker.data.db.entity.LaunchEntry
import com.shema102.spacextracker.data.db.entity.NextLaunchEntry
import com.shema102.spacextracker.data.db.entity.RoadsterEntry

interface SpacexNetworkDataSource {
    val downloadedNextLaunch: LiveData<NextLaunchEntry>
    suspend fun fetchNextLaunch()

    val downloadedLaunches: LiveData<List<LaunchEntry>>
    suspend fun fetchLaunches()

    val downloadedRoadsterEntry: LiveData<RoadsterEntry>
    suspend fun fetchRoadster()
}