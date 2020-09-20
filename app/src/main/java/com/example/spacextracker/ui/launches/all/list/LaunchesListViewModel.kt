package com.example.spacextracker.ui.launches.all.list

import androidx.lifecycle.ViewModel
import com.example.spacextracker.data.repository.SpacexRepository
import com.example.spacextracker.internal.lazyDeferred

class LaunchesListViewModel(
    private val spacexRepository: SpacexRepository
) : ViewModel() {

    val launches by lazyDeferred {
        spacexRepository.getLaunches()
    }
}