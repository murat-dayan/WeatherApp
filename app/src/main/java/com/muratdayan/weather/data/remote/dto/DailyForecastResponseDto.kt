package com.muratdayan.weather.data.remote.dto

data class DailyForecastResponseDto(
    val daily: DailyDto,
    val daily_units: DailyUnitsDto,
    val elevation: Double,
    val generationtime_ms: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)