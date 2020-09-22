package com.example.spacextracker.ui.launches.all.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spacextracker.data.repository.SpacexRepository

class LaunchesListViewModelFactory(
    private val spacexRepository: SpacexRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LaunchesListViewModel(spacexRepository) as T
    }
}