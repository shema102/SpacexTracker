package com.example.spacextracker.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spacextracker.data.db.entity.NextLaunch
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
            _downloadedNextLaunch.postValue(fetchedNextLaunch)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}