package com.muratdayan.weather.domain.repository

import com.muratdayan.weather.core.common.Resource
import com.muratdayan.weather.domain.models.CurrentWeatherModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getCurrentWeather(lat:Double, lon:Double) : Flow<Resource<CurrentWeatherModel>>
}