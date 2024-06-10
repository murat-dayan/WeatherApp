package com.muratdayan.weather.data.remote.services

import com.muratdayan.weather.core.utils.Constants
import com.muratdayan.weather.data.remote.dto.CurrentWeatherResponseDto
import com.muratdayan.weather.data.remote.dto.ForecastResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = Constants.API_KEY,
        @Query("units") units: String = "metric"
    ): CurrentWeatherResponseDto

    @GET("forecast")
    suspend fun getForecastWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey:String = Constants.API_KEY,
        @Query("units") units: String = "metric"
    ) : ForecastResponseDto
}