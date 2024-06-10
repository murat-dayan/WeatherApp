package com.muratdayan.weather.presentation.main

import com.muratdayan.weather.domain.models.CurrentWeatherModel

// This class is responsible for holding the current weather state
data class CurrentWeatherState(
    val currentWeatherModel: CurrentWeatherModel? = null,
    val isLoading: Boolean = false,
    val errorMsg:String?= ""
)