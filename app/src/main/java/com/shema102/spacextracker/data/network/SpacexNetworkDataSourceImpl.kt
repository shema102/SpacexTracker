package com.shema102.spacextracker.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shema102.spacextracker.data.db.entity.*
import com.shema102.spacextracker.internal.NoConnectivityException
import org.threeten.bp.ZonedDateTime


class SpacexNetworkDataSourceImpl(
    private val spacexApiService: SpacexApiService
) : SpacexNetworkDataSource {

    private val _downloadedNextLaunch = MutableLiveData<NextLaunchEntry>()
    override val downloadedNextLaunch: LiveData<NextLaunchEntry>
        get() = _downloadedNextLaunch

    override suspend fun fetchNextLaunch() {
        try {
            val fetchedNextLaunch = spacexApiService
                .getNextLaunchAsync()
                .await()

            // from fetched crew ids fetch crew info
            val fetchedCrew = fetchedNextLaunch.crewId
                .map { crewId ->
                    spacexApiService
                        .getCrewByIdAsync(crewId)
                        .await()
                }.toList()

            fetchedNextLaunch.crewList = fetchedCrew

            // from fetched payload ids fetch payload info
            val fetchedPayloads = fetchedNextLaunch.payloadsId
                .map { payloadId ->
                    spacexApiService
                        .getPayloadByIdAsync(payloadId)
                        .await()
                }.toList()

            fetchedNextLaunch.payloadsList = fetchedPayloads

            // update last fetch time
            fetchedNextLaunch.lastUpdate = ZonedDateTime.now()

            _downloadedNextLaunch.postValue(fetchedNextLaunch)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    private val _downloadedLaunches = MutableLiveData<List<LaunchEntry>>()
    override val downloadedLaunches: LiveData<List<LaunchEntry>>
        get() = _downloadedLaunches

    override suspend fun fetchLaunches() {
        try {
            val fetchedLaunches = spacexApiService
                .getAllLaunchesAsync()
                .await()

            fetchedLaunches.forEach {
                val fetchedCrew = it.crewId
                    .map { crewId ->
                        spacexApiService
                            .getCrewByIdAsync(crewId)
                            .await()
                    }.toList()

                it.crewList = fetchedCrew

                val fetchedPayloads = it.payloadsId
                    .map { payloadId ->
                        spacexApiService
                            .getPayloadByIdAsync(payloadId)
                            .await()
                    }.toList()

                it.payloadsList = fetchedPayloads
            }


            _downloadedLaunches.postValue(fetchedLaunches)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    private val _downloadedRoadster = MutableLiveData<RoadsterEntry>()
    override val downloadedRoadsterEntry: LiveData<RoadsterEntry>
        get() = _downloadedRoadster

    override suspend fun fetchRoadster() {
        try {
            val fetchedRoadster = spacexApiService
                .getRoadsterAsync()
                .await()
            _downloadedRoadster.postValue(fetchedRoadster)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}