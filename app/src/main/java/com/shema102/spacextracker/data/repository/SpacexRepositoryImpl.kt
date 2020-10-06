package com.shema102.spacextracker.data.repository

import androidx.lifecycle.LiveData
import com.shema102.spacextracker.data.db.LaunchesDao
import com.shema102.spacextracker.data.db.NextLaunchDao
import com.shema102.spacextracker.data.db.RoadsterDao
import com.shema102.spacextracker.data.db.entity.LaunchEntry
import com.shema102.spacextracker.data.db.entity.NextLaunchEntry
import com.shema102.spacextracker.data.db.entity.RoadsterEntry
import com.shema102.spacextracker.data.db.unitlocalized.UnitSpecificRoadster
import com.shema102.spacextracker.data.network.SpacexNetworkDataSource
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

    override suspend fun getNextLaunch(): LiveData<NextLaunchEntry> {
        return withContext(Dispatchers.IO) {
            initNextLaunch()
            return@withContext nextLaunchDao.getNextLaunch()
        }
    }

    private fun persistFetchedNextLaunch(fetchedNextLaunch: NextLaunchEntry) {
        GlobalScope.launch(Dispatchers.IO) {
            nextLaunchDao.upsert(fetchedNextLaunch)
        }
    }

    private suspend fun initNextLaunch() {
        val lastUpdate = nextLaunchDao.getNextLaunchLastUpdateTime()
            ?: ZonedDateTime.now().minusYears(100)
        if (isFetchNextLaunchNeeded(lastUpdate))
            fetchNextLaunch()
    }

    private suspend fun fetchNextLaunch() {
        launchesNetworkDataSource.fetchNextLaunch()
    }

    private fun isFetchNextLaunchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val updateWindow = ZonedDateTime.now().minusSeconds(10)
        return lastFetchTime.isBefore(updateWindow)
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

    private suspend fun initRoadster() {
        val lastUpdate = roadsterDao.getRoadsterLastUpdateTime()
            ?: ZonedDateTime.now().minusYears(100)
        if (isFetchRoadsterNeeded(lastUpdate))
            fetchRoadster()
    }

    private suspend fun fetchRoadster() {
        launchesNetworkDataSource.fetchRoadster()
    }

    private fun isFetchRoadsterNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val updateWindow = ZonedDateTime.now().minusMinutes(60)
        return lastFetchTime.isBefore(updateWindow)
    }


    override suspend fun getLaunches(): LiveData<List<LaunchEntry>> {
        return withContext(Dispatchers.IO) {
            initLaunches()
            return@withContext launchesDao.getAllLaunches()
        }
    }


    override suspend fun getLaunchWithId(id: String): LiveData<LaunchEntry> {
        return launchesDao.getLaunchWithId(id)
    }

    private fun persistFetchedLaunches(fetchedLaunches: List<LaunchEntry>) {
        GlobalScope.launch(Dispatchers.IO) {
            launchesDao.upsert(fetchedLaunches)
        }
    }

    private suspend fun initLaunches() {
        if (isFetchLaunchesNeeded(ZonedDateTime.now().minusMinutes(121)))
            fetchLaunches()
    }

    private suspend fun fetchLaunches() {
        launchesNetworkDataSource.fetchLaunches()
    }

    private fun isFetchLaunchesNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(120)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}