package com.muratdayan.cities.domain.repository

import com.muratdayan.cities.core.common.Resource
import com.muratdayan.cities.domain.model.CityResponseModel
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    fun searchCities(searchText:String): Flow<Resource<CityResponseModel>>
}