package com.example.spacextracker.model

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path

interface SpacexApiService {
    @GET("launches/next")
    fun getNextLaunch(): Call<Launch>

    @GET("launches/{id}")
    fun getLaunchById(@Path("id") id: String): Call<Launch>

    @GET("payloads/{id}")
    fun getPayloadById(@Path("id") id: String): Call<Payload>

    @GET("roadster")
    fun getRoadster(): Call<Roadster>

    companion object Factory {
        fun create(): SpacexApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.spacexdata.com/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(SpacexApiService::class.java)
        }
    }
}