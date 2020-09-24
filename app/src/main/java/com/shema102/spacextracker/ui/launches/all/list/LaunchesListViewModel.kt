package com.shema102.spacextracker.ui.launches.all.list

import androidx.lifecycle.ViewModel
import com.shema102.spacextracker.data.repository.SpacexRepository
import com.shema102.spacextracker.internal.lazyDeferred

class LaunchesListViewModel(
    private val spacexRepository: SpacexRepository
) : ViewModel() {

    val launches by lazyDeferred {
        spacexRepository.getLaunches()
    }
}