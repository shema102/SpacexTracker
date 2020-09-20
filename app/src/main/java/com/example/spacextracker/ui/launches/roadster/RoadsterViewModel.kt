package com.example.spacextracker.ui.launches.roadster

import androidx.lifecycle.ViewModel
import com.example.spacextracker.data.repository.SpacexRepository
import com.example.spacextracker.internal.UnitSystem
import com.example.spacextracker.internal.lazyDeferred

class RoadsterViewModel(
    private val spacexRepository: SpacexRepository
) : ViewModel() {
    private val unitSystem = UnitSystem.METRIC// get from settings fragment

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val roadster by lazyDeferred {
        spacexRepository.getRoadster(isMetric)
    }
}