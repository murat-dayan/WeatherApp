package com.muratdayan.weather.data.remote.services

import com.muratdayan.weather.data.remote.dto.DailyForecastResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteoService {

    @GET("forecast")
    suspend fun getDailyForecastWeather(
        @Query("latitude") lat:Double,
        @Query("longitude") lon:Double,
        @Query("daily") daily:String = "temperature_2m_max,uv_index_max",
        @Query("timezone") timezone:String = "auto"
    ) : DailyForecastResponseDto
}