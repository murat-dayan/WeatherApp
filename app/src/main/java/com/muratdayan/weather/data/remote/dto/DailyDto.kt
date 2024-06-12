package com.muratdayan.weather.data.remote.dto

data class DailyDto(
    val temperature_2m_max: List<Double>,
    val time: List<String>
)