package com.example.infotour

data class LocationResponse(
    val place_id: String,
    val osm_type: String,
    val osm_id: String,
    val lat: String,
    val lon: String,
    val display_name: String
)
