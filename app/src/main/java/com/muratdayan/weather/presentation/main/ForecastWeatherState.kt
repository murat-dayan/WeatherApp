package com.muratdayan.weather.presentation.main

import com.muratdayan.weather.domain.models.ForecastModel

data class ForecastWeatherState(
    val forecastModel : ForecastModel?= null,
    val error : String? = "",
    val isLoading : Boolean = false
)
