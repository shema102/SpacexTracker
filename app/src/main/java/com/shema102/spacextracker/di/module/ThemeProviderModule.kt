package com.shema102.spacextracker.di.module

import android.app.Application
import com.shema102.spacextracker.data.provider.ThemeProvider
import com.shema102.spacextracker.data.provider.ThemeProviderImpl
import com.shema102.spacextracker.data.provider.UnitProvider
import com.shema102.spacextracker.data.provider.UnitProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ThemeProviderModule(application: Application) {
    private val spacexApplication = application

    @Provides
    @Singleton
    fun providesThemeProvider(): ThemeProvider = ThemeProviderImpl(spacexApplication)
}