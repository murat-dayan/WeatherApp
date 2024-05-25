package com.muratdayan.weather.data.remote.dto

data class WeatherDto(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)