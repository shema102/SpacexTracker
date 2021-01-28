package com.shema102.spacextracker.di.module

import android.app.Application
import com.shema102.spacextracker.data.network.*
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule(application: Application) {
    private val connectivityInterceptor:ConnectivityInterceptor =
        ConnectivityInterceptorImpl(application)

    @Provides
    fun providesSpacexNetworkDataSource(): SpacexNetworkDataSource =
        SpacexNetworkDataSourceImpl(SpacexApiService.invoke(connectivityInterceptor))
}