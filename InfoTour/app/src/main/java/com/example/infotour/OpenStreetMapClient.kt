package com.example.infotour

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OpenStreetMapClient {
    private const val BASE_URL = "https://openstreetmap.org/api/"

    val apiService: OpenStreetMapService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenStreetMapService::class.java)
    }
}
