package com.example.spacextracker.data.repository

import androidx.lifecycle.LiveData
import com.example.spacextracker.data.db.RoadsterDao
import com.example.spacextracker.data.db.entity.RoadsterEntry
import com.example.spacextracker.data.db.unitlocalized.UnitSpecificRoadster
import com.example.spacextracker.data.network.SpacexNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class SpacexRepositoryImpl(
    private val roadsterDao: RoadsterDao,
    private val launchesNetworkDataSource: SpacexNetworkDataSource
) : SpacexRepository {
    init {
        launchesNetworkDataSource.downloadedRoadsterEntry.observeForever { newRoadster ->
            persistFetchedRoadster(newRoadster)
        }
    }

    override suspend fun getNextLaunch() {
        TODO("Not yet implemented")
    }

    override suspend fun getLaunches() {
        TODO("Not yet implemented")
    }

    override suspend fun getRoadster(metric: Boolean): LiveData<out UnitSpecificRoadster> {
        return withContext(Dispatchers.IO) {
            initRoadster()
            return@withContext if (metric) roadsterDao.getRoadsterMetric()
            else roadsterDao.getRoadsterImperial()
        }
    }

    private fun persistFetchedRoadster(fetchedRoadsterEntry: RoadsterEntry) {
        GlobalScope.launch(Dispatchers.IO) {
            roadsterDao.upsert(fetchedRoadsterEntry)
        }
    }

    private suspend fun initRoadster(){
        if (isFetchRoadsterNeeded(ZonedDateTime.now().minusHours(1)))
            fetchRoadster()
    }

    private suspend fun fetchRoadster(){
        launchesNetworkDataSource.fetchRoadster()
    }

    private fun isFetchRoadsterNeeded(lastFetchTime: ZonedDateTime): Boolean{
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}