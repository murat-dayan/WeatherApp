package com.muratdayan.weather.domain.models

data class ListForecastModel(
    val main: MainModel,
    val weatherList : List<WeatherModel>,
    val dtTxt :String
)
