package com.muratdayan.weather.data.remote.dto

data class CityDto(
    val coord: CoordDto,
    val country: String,
    val id: Long,
    val name: String,
    val population: Int,
    val sunrise: Long,
    val sunset: Long,
    val timezone: Int
)