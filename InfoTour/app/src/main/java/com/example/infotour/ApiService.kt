package com.example.infotour

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("xd")
    fun getLugares(): Call<List<Lugar>>
}
