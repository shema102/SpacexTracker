package com.shema102.spacextracker

import android.app.Application
import com.shema102.spacextracker.data.db.SpacexDatabase
import com.shema102.spacextracker.data.network.*
import com.shema102.spacextracker.data.repository.SpacexRepository
import com.shema102.spacextracker.data.repository.SpacexRepositoryImpl
import com.shema102.spacextracker.ui.launches.all.list.LaunchesListViewModelFactory
import com.shema102.spacextracker.ui.launches.next.NextLaunchViewModelFactory
import com.shema102.spacextracker.ui.launches.roadster.RoadsterViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import com.shema102.spacextracker.ui.launches.all.details.LaunchDetailsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class SpacexApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@SpacexApplication))

        bind() from singleton { SpacexDatabase(instance()) }
        bind() from singleton { instance<SpacexDatabase>().roadsterDao() }
        bind() from singleton { instance<SpacexDatabase>().nextLaunchDao() }
        bind() from singleton { instance<SpacexDatabase>().launchesDao() }
        bind<ConnectivityInterceptor>() with singleton {
            ConnectivityInterceptorImpl(instance())
        }
        bind() from singleton { SpacexApiService(instance()) }
        bind<SpacexNetworkDataSource>() with singleton {
            SpacexNetworkDataSourceImpl(instance())
        }
        bind<SpacexRepository>() with singleton {
            SpacexRepositoryImpl(
                instance(),
                instance(),
                instance(),
                instance()
            )
        }

        bind() from provider { RoadsterViewModelFactory(instance()) }
        bind() from provider { NextLaunchViewModelFactory(instance()) }
        bind() from provider { LaunchesListViewModelFactory(instance()) }
        bind() from factory() { launchId: String ->
            LaunchDetailsViewModelFactory(
                launchId,
                instance()
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}