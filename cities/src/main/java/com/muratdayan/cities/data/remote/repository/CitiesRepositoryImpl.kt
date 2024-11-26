package com.muratdayan.cities.data.remote.repository

import com.muratdayan.cities.data.remote.mapper.toCityResponseModel
import com.muratdayan.cities.data.remote.service.CitiesService
import com.muratdayan.cities.domain.model.CityResponseModel
import com.muratdayan.cities.domain.repository.CitiesRepository
import com.muratdayan.common.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val citiesService: CitiesService
) : CitiesRepository{
    override fun searchCities(searchText: String): Flow<Resource<CityResponseModel>> = flow {
        emit(Resource.Loading())

        try {
            val cityResponseDto = citiesService.searchCity(searchText)
            val cityResponseModel = cityResponseDto.toCityResponseModel()

            emit(Resource.Success(cityResponseModel))

        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
        .catch {error->
            emit(Resource.Error(error.message.toString()))
        }
}