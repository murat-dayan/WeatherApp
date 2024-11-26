package com.muratdayan.cities.domain.repository

import com.muratdayan.cities.domain.model.CityResponseModel
import com.muratdayan.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    fun searchCities(searchText:String): Flow<Resource<CityResponseModel>>
}