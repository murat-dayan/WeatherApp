package com.muratdayan.weather.data.remote.dto

data class CurrentDto(
    val interval: Int,
    val temperature_2m: Double,
    val time: String
)