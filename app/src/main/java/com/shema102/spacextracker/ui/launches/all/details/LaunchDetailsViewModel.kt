package com.shema102.spacextracker.ui.launches.all.details

import androidx.lifecycle.ViewModel
import com.shema102.spacextracker.data.repository.SpacexRepository
import com.shema102.spacextracker.internal.lazyDeferred

class LaunchDetailsViewModel(
    private val spacexRepository: SpacexRepository,
    private val launchId: String

) : ViewModel() {

    val launches by lazyDeferred {
        spacexRepository.getLaunchWithId(launchId)
    }
}