package com.muratdayan.weather.presentation.weather_detail

import com.muratdayan.weather.domain.models.DailyModel

data class DailyWeatherState(
    val dailymodel: DailyModel?= null,
    val isLoading: Boolean = false,
    val error: String? = ""
)
