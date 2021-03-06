package com.shema102.spacextracker.ui.launches.all.details

import androidx.lifecycle.ViewModel
import com.shema102.spacextracker.data.repository.SpacexRepository
import com.shema102.spacextracker.internal.lazyDeferred

class LaunchDetailsViewModel(
    private val spacexRepository: SpacexRepository
) : ViewModel() {
    lateinit var launchId: String

    val launch by lazyDeferred {
        spacexRepository.getLaunchWithId(launchId)
    }
}