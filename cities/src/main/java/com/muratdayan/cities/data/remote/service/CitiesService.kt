package com.muratdayan.cities.data.remote.service

import com.muratdayan.cities.data.remote.model.CityResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CitiesService {

    @GET("locations/cities")
    suspend fun searchCity(
        @Query("search") searchText:String
    ) : CityResponseDto
}