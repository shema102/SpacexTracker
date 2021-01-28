package com.shema102.spacextracker.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shema102.spacextracker.data.provider.UnitProvider
import com.shema102.spacextracker.data.repository.SpacexRepository
import com.shema102.spacextracker.ui.launches.roadster.RoadsterViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoadsterViewModelFactory @Inject constructor(
    private val spacexRepository: SpacexRepository,
    private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RoadsterViewModel(spacexRepository, unitProvider) as T
    }
}

