package com.shema102.spacextracker.di.component

import com.shema102.spacextracker.di.factory.LaunchDetailsViewModelFactory
import com.shema102.spacextracker.di.module.*
import com.shema102.spacextracker.ui.MainActivity
import com.shema102.spacextracker.ui.launches.all.details.LaunchDetailsFragment
import com.shema102.spacextracker.ui.launches.all.list.LaunchesListFragment
import com.shema102.spacextracker.ui.launches.next.NextLaunchFragment
import com.shema102.spacextracker.ui.launches.roadster.RoadsterFragment
import com.shema102.spacextracker.ui.settings.SettingsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DatabaseModule::class,
        LaunchesModule::class,
        UnitProviderModule::class,
        ThemeProviderModule::class,
        DataSourceModule::class]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(nextLaunchFragment: NextLaunchFragment)

    fun inject(launchesListFragment: LaunchesListFragment)

    fun inject(launchDetailsFragment: LaunchDetailsFragment)

    fun inject(settingsFragment: SettingsFragment)

    fun inject(roadsterFragment: RoadsterFragment)

    fun inject(launchDetailsViewModelFactory: LaunchDetailsViewModelFactory)
}