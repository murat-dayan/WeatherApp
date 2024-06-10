package com.muratdayan.weather.domain.models

data class WeatherModel(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
