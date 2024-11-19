package com.muratdayan.cities.presentation.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.cities.core.common.Resource
import com.muratdayan.cities.domain.model.CityResponseModel
import com.muratdayan.cities.domain.usecase.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase
) : ViewModel(){

    private val _searchedCitiesList = MutableStateFlow<Resource<CityResponseModel>>(Resource.Idle())
    val searchedCitiesList: StateFlow<Resource<CityResponseModel>> = _searchedCitiesList


    fun searchCity(searchText:String){
        searchCityUseCase.invoke(searchText).onEach { result->
            _searchedCitiesList.value = result
        }.launchIn(viewModelScope)
    }
}