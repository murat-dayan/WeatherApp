package com.muratdayan.weather.presentation.main

import com.muratdayan.weather.domain.models.CurrentWeatherModel

data class CurrentWeatherState(
    val currentWeatherModel: CurrentWeatherModel? = null,
    val isLoading: Boolean = false,
    val errorMsg:String?= ""
)