package com.shema102.spacextracker.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shema102.spacextracker.data.repository.SpacexRepository
import com.shema102.spacextracker.ui.launches.all.details.LaunchDetailsFragment
import com.shema102.spacextracker.ui.launches.all.details.LaunchDetailsViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaunchDetailsViewModelFactory @Inject constructor(
    private val spacexRepository: SpacexRepository
) : ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LaunchDetailsViewModel(spacexRepository) as T
    }


}
