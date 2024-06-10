package com.muratdayan.weather.data.remote.repository

import com.muratdayan.weather.core.common.Resource
import com.muratdayan.weather.data.remote.mappers.toCurrentWeatherModel
import com.muratdayan.weather.data.remote.services.IWeatherService
import com.muratdayan.weather.domain.models.CurrentWeatherModel
import com.muratdayan.weather.domain.repository.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

// This class is responsible for fetching data from the API and converting it to the domain model.
@ViewModelScoped
class RepositoryImpl @Inject constructor(
    private val iWeatherService: IWeatherService
) : WeatherRepository{
    override fun getCurrentWeather(
        lat: Double,
        lon: Double
    ): Flow<Resource<CurrentWeatherModel>> = flow {
        emit(Resource.Loading())

        val currentWeatherResponseDto = iWeatherService.getCurrentWeather(lat,lon)

        val currentWeatherModel = currentWeatherResponseDto.toCurrentWeatherModel()

        emit(Resource.Success(currentWeatherModel))
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }
}