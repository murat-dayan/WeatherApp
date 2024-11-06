package com.muratdayan.weather.domain.models

data class CurrentWeatherModel (
    val current: CurrentModel,
    val hourly:HourlyModel
)