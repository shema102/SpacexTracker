package com.example.spacextracker.ui.launches.next

import androidx.lifecycle.ViewModel
import com.example.spacextracker.data.repository.SpacexRepository
import com.example.spacextracker.internal.lazyDeferred

class NextLaunchViewModel(
    private val spacexRepository: SpacexRepository
) : ViewModel() {

    val nextLaunch by lazyDeferred {
        spacexRepository.getNextLaunch()
    }
}