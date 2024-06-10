package com.muratdayan.weather.domain.models

data class ForecastModel(
    val city: CityForecastModel,
    val forecastList : List<ListForecastModel>
)
