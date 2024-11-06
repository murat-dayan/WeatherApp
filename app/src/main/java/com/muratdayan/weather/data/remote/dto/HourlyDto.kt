package com.muratdayan.weather.data.remote.dto

data class HourlyDto(
    val temperature_2m: List<Double>,
    val time: List<String>
)