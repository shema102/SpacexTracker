package com.example.spacextracker.data.repository

import androidx.lifecycle.LiveData
import com.example.spacextracker.data.db.LaunchesDao
import com.example.spacextracker.data.db.NextLaunchDao
import com.example.spacextracker.data.db.RoadsterDao
import com.example.spacextracker.data.db.entity.LaunchEntry
import com.example.spacextracker.data.db.entity.NextLaunch
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
    private val nextLaunchDao: NextLaunchDao,
    private val launchesDao: LaunchesDao,
    private val launchesNetworkDataSource: SpacexNetworkDataSource
) : SpacexRepository {
    init {
        launchesNetworkDataSource.downloadedRoadsterEntry.observeForever { newRoadster ->
            persistFetchedRoadster(newRoadster)
        }

        launchesNetworkDataSource.downloadedNextLaunch.observeForever { newNextLaunch ->
            persistFetchedNextLaunch(newNextLaunch)
        }

        launchesNetworkDataSource.downloadedLaunches.observeForever { launches ->
            persistFetchedLaunches(launches)
        }
    }

    override suspend fun getNextLaunch(): LiveData<NextLaunch> {
        return withContext(Dispatchers.IO) {
            initNextLaunch()
            return@withContext nextLaunchDao.getNextLaunch()
        }
    }


    override suspend fun getLaunches(): LiveData<List<LaunchEntry>> {
        return withContext(Dispatchers.IO) {
            initLaunches()
            return@withContext launchesDao.getAllLaunches()
        }
    }

    override suspend fun getRoadster(metric: Boolean): LiveData<out UnitSpecificRoadster> {
        return withContext(Dispatchers.IO) {
            initRoadster()
            return@withContext if (metric) roadsterDao.getRoadsterMetric()
            else roadsterDao.getRoadsterImperial()
        }
    }

    private fun persistFetchedNextLaunch(fetchedNextLaunch: NextLaunch) {
        GlobalScope.launch(Dispatchers.IO) {
            nextLaunchDao.upsert(fetchedNextLaunch)
        }
    }

    private suspend fun initNextLaunch() {
        if (isFetchNextLaunchNeeded(ZonedDateTime.now().minusHours(1)))
            fetchNextLaunch()
    }

    private suspend fun fetchNextLaunch() {
        launchesNetworkDataSource.fetchNextLaunch()
    }

    private fun isFetchNextLaunchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }


    private fun persistFetchedRoadster(fetchedRoadsterEntry: RoadsterEntry) {
        GlobalScope.launch(Dispatchers.IO) {
            roadsterDao.upsert(fetchedRoadsterEntry)
        }
    }

    private suspend fun initRoadster() {
        if (isFetchRoadsterNeeded(ZonedDateTime.now().minusHours(1)))
            fetchRoadster()
    }

    private suspend fun fetchRoadster() {
        launchesNetworkDataSource.fetchRoadster()
    }

    private fun isFetchRoadsterNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }


    private fun persistFetchedLaunches(fetchedLaunches: List<LaunchEntry>) {
        GlobalScope.launch(Dispatchers.IO) {
            launchesDao.upsert(fetchedLaunches)
        }
    }

    private suspend fun initLaunches() {
        if (isFetchLaunchesNeeded(ZonedDateTime.now().minusHours(1)))
            fetchLaunches()
    }

    private suspend fun fetchLaunches() {
        launchesNetworkDataSource.fetchLaunches()
    }

    private fun isFetchLaunchesNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}