package com.muratdayan.weather.data.remote.dto

data class CurrentWeatherRespondDto(
    val current: CurrentDto,
    val current_units: CurrentUnitsDto,
    val elevation: Double,
    val generationtime_ms: Double,
    val hourly: HourlyDto,
    val hourly_units: HourlyUnitsDto,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)