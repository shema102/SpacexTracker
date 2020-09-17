package com.example.spacextracker.data.network.response

import com.example.spacextracker.data.db.entity.Launch
import com.example.spacextracker.data.db.entity.Payload
import com.example.spacextracker.data.db.entity.Roadster
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory


const val BASE_URL = "https://api.spacexdata.com/v4/"

interface SpacexApiService {
    @GET("launches")
    fun getAllLaunches(): Deferred<List<Launch>>

    @GET("launches/next")
    fun getNextLaunch(): Deferred<Launch>

    @GET("launches/{id}")
    fun getLaunchById(@Path("id") id: String): Deferred<Launch>

    @GET("payloads/{id}")
    fun getPayloadById(@Path("id") id: String): Deferred<Payload>

    @GET("roadster")
    fun getRoadster(): Deferred<Roadster>

    companion object Factory {
        operator fun invoke(): SpacexApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(SpacexApiService::class.java)
        }
    }
}