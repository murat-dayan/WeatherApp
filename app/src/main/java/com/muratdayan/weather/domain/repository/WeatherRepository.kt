package com.muratdayan.weather.domain.repository

import com.muratdayan.weather.core.common.Resource
import com.muratdayan.weather.domain.models.CurrentWeatherModel
import kotlinx.coroutines.flow.Flow

// it's used to abstract the data layer from the domain layer, allowing for easier testing and maintenance
interface WeatherRepository {

    fun getCurrentWeather(lat:Double, lon:Double) : Flow<Resource<CurrentWeatherModel>>
}