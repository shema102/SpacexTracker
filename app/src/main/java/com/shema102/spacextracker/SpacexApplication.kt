package com.shema102.spacextracker

import android.app.Application
import androidx.preference.PreferenceManager
import com.jakewharton.threetenabp.AndroidThreeTen
import com.shema102.spacextracker.di.component.DaggerAppComponent
import com.shema102.spacextracker.di.module.*
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SpacexApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var _androidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector() = _androidInjector



    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .unitProviderModule(UnitProviderModule(this))
            .themeProviderModule(ThemeProviderModule(this))
            .dataSourceModule(DataSourceModule(this))
            .databaseModule(DatabaseModule(this))
            .launchesModule(LaunchesModule()).build().inject(this)

        AndroidThreeTen.init(this@SpacexApplication)

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}