package com.muratdayan.weather.data.remote.repository

import com.muratdayan.weather.data.remote.services.IWeatherService
import com.muratdayan.weather.domain.repository.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RepositoryImpl @Inject constructor(
    private val iWeatherService: IWeatherService
) : WeatherRepository{


}