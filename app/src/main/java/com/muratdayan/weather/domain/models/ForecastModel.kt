package com.muratdayan.weather.domain.models

import java.io.Serializable

data class ForecastModel(
    val city: CityForecastModel,
    val forecastList : List<ListForecastModel>
) : Serializable
