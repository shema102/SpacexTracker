package com.shema102.spacextracker.di.module

import android.app.Application
import com.shema102.spacextracker.data.provider.UnitProvider
import com.shema102.spacextracker.data.provider.UnitProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UnitProviderModule(application: Application) {
    private val spacexApplication = application

    @Provides
    @Singleton
    fun providesUnitProvider(): UnitProvider = UnitProviderImpl(spacexApplication)
}