package com.muratdayan.cities.domain.usecase

import com.muratdayan.cities.domain.repository.CitiesRepository
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(
    private val citiesRepository: CitiesRepository
) {
    operator fun invoke(searchText:String)  = citiesRepository.searchCities(searchText)
}