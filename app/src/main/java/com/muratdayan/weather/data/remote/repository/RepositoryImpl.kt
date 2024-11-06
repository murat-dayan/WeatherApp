package com.muratdayan.weather.data.remote.repository

import com.muratdayan.weather.core.common.Resource
import com.muratdayan.weather.data.remote.mappers.toCurrentWeatherModel
import com.muratdayan.weather.data.remote.mappers.toDailyModel
import com.muratdayan.weather.data.remote.services.OpenMeteoService
import com.muratdayan.weather.domain.models.CurrentWeatherModel
import com.muratdayan.weather.domain.models.DailyModel
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
    private val openMeteoService: OpenMeteoService
) : WeatherRepository {


    override fun getDailyForecastWeather(lat: Double, lon: Double): Flow<Resource<DailyModel>> =
        flow {
            emit(Resource.Loading())

            val dailyForecastResponseDto = openMeteoService.getDailyForecastWeather(lat, lon)

            val dailyModel = dailyForecastResponseDto.daily.toDailyModel()
            emit(Resource.Success(dailyModel))
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(Resource.Error(it.message.toString()))
        }

    override fun getCurrentAndHourlyWeather(
        lat: Double,
        lon: Double
    ): Flow<Resource<CurrentWeatherModel>> = flow {
        emit(Resource.Loading())

        val currentWeatherRespondDto = openMeteoService.getCurrentAndHourlyWeather(lat, lon)

        val currentWeatherModel = currentWeatherRespondDto.toCurrentWeatherModel()
        emit(Resource.Success(currentWeatherModel))

    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }
}