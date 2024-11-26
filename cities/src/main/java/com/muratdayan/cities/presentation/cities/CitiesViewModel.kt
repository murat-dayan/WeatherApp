package com.muratdayan.cities.presentation.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.cities.domain.model.CityResponseModel
import com.muratdayan.cities.domain.usecase.SearchCityUseCase
import com.muratdayan.common.data.local.entity.CityEntity
import com.muratdayan.common.domain.repository.CitiesRepository
import com.muratdayan.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    private val citiesRepository: CitiesRepository
) : ViewModel() {

    private val _searchedCitiesList = MutableStateFlow<Resource<CityResponseModel>>(Resource.Idle())
    val searchedCitiesList: StateFlow<Resource<CityResponseModel>> = _searchedCitiesList

    private val _insertResultState = MutableStateFlow<Resource<Boolean>>(Resource.Idle())
    val insertResult: StateFlow<Resource<Boolean>> = _insertResultState


    fun searchCity(searchText: String) {
        searchCityUseCase.invoke(searchText).onEach { result: Resource<CityResponseModel> ->
            _searchedCitiesList.value = result
        }.launchIn(viewModelScope)
    }

    fun insertCity(city: CityEntity) {
        citiesRepository.insertCity(city).onEach { result ->
            _insertResultState.value = result
        }.launchIn(viewModelScope)
    }
}