package com.muratdayan.weather.domain.models

data class DailyModel(
    val temperatures: List<Double>,
    val time: List<String>,
    val uv_index_max : List<Double>
)
