package com.muratdayan.common.domain.repository

import com.muratdayan.common.data.local.entity.CityEntity
import com.muratdayan.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    fun getAllCities(): Flow<Resource<List<CityEntity>>>
    fun insertCity(city: CityEntity) : Flow<Resource<Boolean>>

}