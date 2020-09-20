package com.example.spacextracker.ui.launches.roadster

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spacextracker.data.repository.SpacexRepository


class RoadsterViewModelFactory(
    private val spacexRepository: SpacexRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RoadsterViewModel(spacexRepository) as T
    }
}

