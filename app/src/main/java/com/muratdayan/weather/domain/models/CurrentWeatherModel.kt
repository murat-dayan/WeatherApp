package com.muratdayan.weather.domain.models

data class CurrentWeatherModel(
    val base: String,
    val cod: Int,
    val dt: Int,
    val id: Int,
    val name: String,
    val timezone: Int,
    val visibility: Int,
    val main:MainModel
)
