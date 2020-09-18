package com.example.spacextracker

import android.app.Application
import com.example.spacextracker.data.db.SpacexDatabase
import com.example.spacextracker.data.network.*
import com.example.spacextracker.data.repository.SpacexRepository
import com.example.spacextracker.data.repository.SpacexRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class SpacexApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@SpacexApplication))

        bind() from singleton { SpacexDatabase(instance()) }
        bind() from singleton { instance<SpacexDatabase>().roadsterDao() }
        bind<ConnectivityInterceptor>() with singleton {
            ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { SpacexApiService(instance()) }
        bind<SpacexNetworkDataSource>() with singleton {
            SpacexNetworkDataSourceImpl(instance()) }
        bind<SpacexRepository>() with singleton { SpacexRepositoryImpl(instance(), instance()) }
    }
}