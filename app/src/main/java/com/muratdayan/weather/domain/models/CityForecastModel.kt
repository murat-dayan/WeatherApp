package com.muratdayan.weather.domain.models

data class CityForecastModel(
    val id:Long,
    val name:String,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
