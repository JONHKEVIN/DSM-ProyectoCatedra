package com.example.infotour

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenStreetMapService {
    @GET("geocoding/v1/search.php")
    fun searchLocation(
        @Query("q") location: String,
        @Query("format") format: String = "json"
    ): Call<List<LocationResponse>>
}
