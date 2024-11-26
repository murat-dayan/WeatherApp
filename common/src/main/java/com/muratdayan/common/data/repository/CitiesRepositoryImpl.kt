package com.muratdayan.common.data.repository

import com.muratdayan.common.data.local.entity.CityEntity
import com.muratdayan.common.data.local.service.CityDao
import com.muratdayan.common.domain.repository.CitiesRepository
import com.muratdayan.common.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val cityDao: CityDao

): CitiesRepository {
    override fun getAllCities(): Flow<Resource<List<CityEntity>>> = flow {
        emit(Resource.Loading())

        val citiesList = cityDao.getAllCities()
        emit(Resource.Success(citiesList))
    }.flowOn(Dispatchers.Default)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }

    override fun insertCity(city: CityEntity)= flow {
        emit(Resource.Loading())

        cityDao.insertCity(city)
        emit(Resource.Success(true))
    }.flowOn(Dispatchers.Default)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }


}