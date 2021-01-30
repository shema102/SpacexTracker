package com.shema102.spacextracker.di.module

import com.shema102.spacextracker.ui.launches.all.details.LaunchDetailsFragment
import com.shema102.spacextracker.ui.launches.all.list.LaunchesListFragment
import com.shema102.spacextracker.ui.launches.next.NextLaunchFragment
import com.shema102.spacextracker.ui.launches.roadster.RoadsterFragment
import com.shema102.spacextracker.ui.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentInjector {
    @ContributesAndroidInjector
    abstract fun bindNextLaunchFragment(): NextLaunchFragment

    @ContributesAndroidInjector
    abstract fun bindRoadsterFragment(): RoadsterFragment

    @ContributesAndroidInjector
    abstract fun bindLaunchDetailsFragment(): LaunchDetailsFragment

    @ContributesAndroidInjector
    abstract fun bindLaunchesListFragment(): LaunchesListFragment

    @ContributesAndroidInjector
    abstract fun bindSettingsFragment(): SettingsFragment
}