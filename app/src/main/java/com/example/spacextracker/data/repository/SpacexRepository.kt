package com.example.spacextracker.data.repository

import androidx.lifecycle.LiveData
import com.example.spacextracker.data.db.entity.LaunchEntry
import com.example.spacextracker.data.db.entity.NextLaunch
import com.example.spacextracker.data.db.unitlocalized.UnitSpecificRoadster

interface SpacexRepository {
    suspend fun getNextLaunch(): LiveData<NextLaunch>

    suspend fun getLaunches(): LiveData<List<LaunchEntry>>

    suspend fun getRoadster(metric: Boolean): LiveData<out UnitSpecificRoadster>

    suspend fun getLaunchWithId(id: String): LiveData<LaunchEntry>
}