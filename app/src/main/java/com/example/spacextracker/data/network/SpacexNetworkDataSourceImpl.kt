package com.example.spacextracker.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spacextracker.data.db.entity.*
import com.example.spacextracker.internal.NoConnectivityException

class SpacexNetworkDataSourceImpl(
    private val spacexApiService: SpacexApiService
) : SpacexNetworkDataSource {

    private val _downloadedNextLaunch = MutableLiveData<NextLaunch>()
    override val downloadedNextLaunch: LiveData<NextLaunch>
        get() = _downloadedNextLaunch

    override suspend fun fetchNextLaunch() {
        try {
            val fetchedNextLaunch = spacexApiService
                .getNextLaunchAsync()
                .await()

            val fetchedCrew = fetchedNextLaunch.crewId
                .map { crewId ->
                    spacexApiService
                        .getCrewByIdAsync(crewId)
                        .await()
                }.toList()

            fetchedNextLaunch.crewList = fetchedCrew

            val fetchedPayloads = fetchedNextLaunch.payloadsId
                .map { payloadId ->
                    spacexApiService
                        .getPayloadByIdAsync(payloadId)
                        .await()
                }.toList()

            fetchedNextLaunch.payloadsList = fetchedPayloads

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