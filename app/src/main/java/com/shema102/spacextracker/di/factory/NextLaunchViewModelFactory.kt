package com.shema102.spacextracker.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shema102.spacextracker.data.repository.SpacexRepository
import com.shema102.spacextracker.ui.launches.next.NextLaunchViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NextLaunchViewModelFactory @Inject constructor(
    private val spacexRepository: SpacexRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NextLaunchViewModel(spacexRepository) as T
    }
}