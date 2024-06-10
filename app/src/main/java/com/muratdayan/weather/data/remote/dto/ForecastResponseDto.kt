package com.muratdayan.weather.data.remote.dto

data class ForecastResponseDto(
    val city: CityDto,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastListDto>,
    val message: Int
)