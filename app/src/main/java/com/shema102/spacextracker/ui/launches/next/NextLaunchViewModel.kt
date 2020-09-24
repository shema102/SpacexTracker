package com.shema102.spacextracker.ui.launches.next

import androidx.lifecycle.ViewModel
import com.shema102.spacextracker.data.repository.SpacexRepository
import com.shema102.spacextracker.internal.lazyDeferred

class NextLaunchViewModel(
    private val spacexRepository: SpacexRepository
) : ViewModel() {

    val nextLaunch by lazyDeferred {
        spacexRepository.getNextLaunch()
    }
}