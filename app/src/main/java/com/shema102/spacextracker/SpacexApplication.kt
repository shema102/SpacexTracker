package com.shema102.spacextracker

import android.app.Application
import androidx.preference.PreferenceManager
import com.jakewharton.threetenabp.AndroidThreeTen
import com.shema102.spacextracker.di.component.ApplicationComponent
import com.shema102.spacextracker.di.component.DaggerApplicationComponent
import com.shema102.spacextracker.di.module.LaunchesModule
import com.shema102.spacextracker.di.module.DataSourceModule
import com.shema102.spacextracker.di.module.DatabaseModule
import com.shema102.spacextracker.di.module.ThemeProviderModule
import com.shema102.spacextracker.di.module.UnitProviderModule

class SpacexApplication : Application(){

    companion object {
        lateinit var instance: SpacexApplication
    }

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        applicationComponent = DaggerApplicationComponent.builder()
            .unitProviderModule(UnitProviderModule(this))
            .themeProviderModule(ThemeProviderModule(this))
            .dataSourceModule(DataSourceModule(this))
            .databaseModule(DatabaseModule(this))
            .launchesModule(LaunchesModule()).build()

        AndroidThreeTen.init(this)

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}