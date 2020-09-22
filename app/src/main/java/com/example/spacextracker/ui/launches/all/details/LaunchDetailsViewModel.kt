package com.example.spacextracker.ui.launches.all.details

import androidx.lifecycle.ViewModel
import com.example.spacextracker.data.repository.SpacexRepository
import com.example.spacextracker.internal.lazyDeferred

class LaunchDetailsViewModel(
    private val spacexRepository: SpacexRepository,
    private val launchId: String

) : ViewModel() {

    val launches by lazyDeferred {
        spacexRepository.getLaunchWithId(launchId)
    }
}