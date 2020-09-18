package com.example.spacextracker.data.network

import androidx.lifecycle.LiveData
import com.example.spacextracker.data.db.entity.LaunchEntry
import com.example.spacextracker.data.db.entity.NextLaunch
import com.example.spacextracker.data.db.entity.RoadsterEntry

interface SpacexNetworkDataSource {
    val downloadedNextLaunch: LiveData<NextLaunch>
    suspend fun fetchNextLaunch()

    val downloadedLaunches: LiveData<List<LaunchEntry>>
    suspend fun fetchLaunches()

    val downloadedRoadsterEntry: LiveData<RoadsterEntry>
    suspend fun fetchRoadster()
}