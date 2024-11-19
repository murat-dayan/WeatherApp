package com.muratdayan.cities.data.remote.repository

import com.muratdayan.cities.data.remote.service.CitiesService
import com.muratdayan.cities.domain.repository.CitiesRepository
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val citiesService: CitiesService
) : CitiesRepository{
}