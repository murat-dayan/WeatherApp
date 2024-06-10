package com.muratdayan.weather.domain.use_cases

import com.muratdayan.weather.domain.repository.WeatherRepository
import javax.inject.Inject

// This class is responsible for fetching the current weather data from the repository
class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    // allows custom behavior to be defined when the class is called
    operator fun invoke(lat:Double,lon:Double) = weatherRepository.getCurrentWeather(lat,lon)
}