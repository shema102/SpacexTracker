package com.example.spacextracker.model

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path

interface SpacexApiService {
    @GET("launches/next")
    fun getNextLaunch(): Call<Launch>

//    @GET("/capsules/:{id}")
//    fun getCapsuleById(id: @Path("id") Int): Call<Capsule>

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