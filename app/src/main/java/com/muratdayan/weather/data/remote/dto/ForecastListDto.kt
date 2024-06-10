package com.muratdayan.weather.data.remote.dto

data class ForecastListDto(
    val clouds: CloudsDto,
    val dt: Int,
    val dt_txt: String,
    val main: MainDto,
    val pop: Double,
    val rain: RainDto,
    val sys: SysDto,
    val visibility: Int,
    val weather: List<WeatherDto>,
    val wind: WindDto
)