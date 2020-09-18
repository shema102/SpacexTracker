package com.example.spacextracker.data.network

import androidx.lifecycle.LiveData
import com.example.spacextracker.data.db.entity.NextLaunch

interface SpacexNetworkDataSource {
    val downloadedNextLaunch: LiveData<NextLaunch>

    suspend fun fetchNextLaunch()
}