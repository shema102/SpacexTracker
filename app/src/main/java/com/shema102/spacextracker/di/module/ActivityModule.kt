package com.shema102.spacextracker.di.module

import com.shema102.spacextracker.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}