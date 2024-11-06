package com.muratdayan.weather.domain.models

data class HourlyModel(
    val temperatures: List<Double>,
    val time: List<String>
)
