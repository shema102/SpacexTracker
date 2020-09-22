package com.example.spacextracker.ui.launches.all.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spacextracker.data.repository.SpacexRepository

class LaunchDetailsViewModelFactory(
    private val launchId: String,
    private val spacexRepository: SpacexRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LaunchDetailsViewModel(spacexRepository, launchId) as T
    }
}
