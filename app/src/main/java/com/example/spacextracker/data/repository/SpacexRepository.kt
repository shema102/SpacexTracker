package com.example.spacextracker.data.repository

import androidx.lifecycle.LiveData
import com.example.spacextracker.data.db.unitlocalized.UnitSpecificRoadster

interface SpacexRepository {
    suspend fun getNextLaunch()

    suspend fun getLaunches()

    suspend fun getRoadster(metric: Boolean): LiveData<out UnitSpecificRoadster>
}