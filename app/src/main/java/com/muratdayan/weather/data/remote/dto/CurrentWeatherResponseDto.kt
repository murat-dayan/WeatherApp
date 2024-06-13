package com.muratdayan.weather.data.remote.dto

data class CurrentWeatherResponseDto(
    val base: String,
    val clouds: CloudsDto,
    val cod: Int,
    val coord: CoordDto,
    val dt: Long,
    val id: Int,
    val main: MainDto,
    val name: String,
    val rain: RainDto,
    val sys: SysDto,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDto>,
    val wind: WindDto
)