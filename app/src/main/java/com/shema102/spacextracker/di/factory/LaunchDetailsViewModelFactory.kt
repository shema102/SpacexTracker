package com.shema102.spacextracker.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shema102.spacextracker.SpacexApplication
import com.shema102.spacextracker.data.repository.SpacexRepository
import com.shema102.spacextracker.ui.launches.all.details.LaunchDetailsViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaunchDetailsViewModelFactory constructor(
    private val launchId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Inject
    lateinit var spacexRepository: SpacexRepository

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        SpacexApplication.instance.applicationComponent.inject(this)
        return LaunchDetailsViewModel(launchId, spacexRepository) as T
    }


}
