package com.example.spacextracker.ui.launches.next

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spacextracker.data.repository.SpacexRepository

class NextLaunchViewModelFactory(
    private val spacexRepository: SpacexRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NextLaunchViewModel(spacexRepository) as T
    }
}