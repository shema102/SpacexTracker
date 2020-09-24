package com.shema102.spacextracker.data.network

import com.shema102.spacextracker.data.db.entity.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient


const val BASE_URL = "https://api.spacexdata.com/v4/"

interface SpacexApiService {
    @GET("launches")
    fun getAllLaunchesAsync(): Deferred<List<LaunchEntry>>

    @GET("launches/next")
    fun getNextLaunchAsync(): Deferred<NextLaunch>

    @GET("launches/{id}")
    fun getLaunchByIdAsync(@Path("id") id: String): Deferred<LaunchEntry>

    @GET("roadster")
    fun getRoadsterAsync(): Deferred<RoadsterEntry>

    @GET("payloads/{id}")
    fun getPayloadByIdAsync(@Path("id") id: String): Deferred<Payload>

    @GET("crew/{id}")
    fun getCrewByIdAsync(@Path("id") id: String): Deferred<Crew>

    companion object Factory {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): SpacexApiService {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(SpacexApiService::class.java)
        }
    }
}