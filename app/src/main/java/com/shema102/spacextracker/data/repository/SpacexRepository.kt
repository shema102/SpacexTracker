package com.shema102.spacextracker.data.repository

import androidx.lifecycle.LiveData
import com.shema102.spacextracker.data.db.entity.LaunchEntry
import com.shema102.spacextracker.data.db.entity.NextLaunchEntry
import com.shema102.spacextracker.data.db.unitlocalized.UnitSpecificRoadster

interface SpacexRepository {
    suspend fun getNextLaunch(): LiveData<NextLaunchEntry>

    suspend fun getLaunches(): LiveData<List<LaunchEntry>>

    suspend fun getRoadster(metric: Boolean): LiveData<out UnitSpecificRoadster>

    suspend fun getLaunchWithId(id: String): LiveData<LaunchEntry>
}