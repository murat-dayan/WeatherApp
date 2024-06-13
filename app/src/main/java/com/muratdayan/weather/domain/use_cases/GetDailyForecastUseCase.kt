package com.muratdayan.weather.domain.use_cases

import com.muratdayan.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetDailyForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    operator fun invoke(lat: Double, lon: Double) = repository.getDailyForecastWeather(lat, lon)
}