package com.example.infotour

data class Lugar(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val address: String,
    val photos: List<String>
)
