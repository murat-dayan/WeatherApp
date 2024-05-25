package com.muratdayan.weather.domain.use_cases

import com.muratdayan.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    operator fun invoke(lat:Double,lon:Double) = weatherRepository.getCurrentWeather(lat,lon)
}