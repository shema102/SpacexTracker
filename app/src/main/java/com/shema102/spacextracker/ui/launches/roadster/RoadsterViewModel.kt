package com.shema102.spacextracker.ui.launches.roadster

import androidx.lifecycle.ViewModel
import com.shema102.spacextracker.data.provider.UnitProvider
import com.shema102.spacextracker.data.repository.SpacexRepository
import com.shema102.spacextracker.internal.UnitSystem
import com.shema102.spacextracker.internal.lazyDeferred

class RoadsterViewModel(
    private val spacexRepository: SpacexRepository,
    private val unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val roadster by lazyDeferred {
        spacexRepository.getRoadster(isMetric)
    }
}