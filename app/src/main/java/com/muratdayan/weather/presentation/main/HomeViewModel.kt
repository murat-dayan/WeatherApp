package com.muratdayan.weather.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.weather.core.common.Resource
import com.muratdayan.weather.domain.use_cases.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

// used to handle the business logic related to fetching and managing the current weather data
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,

)  :ViewModel(){

    private val _currentWeatherState = MutableStateFlow(CurrentWeatherState())
    val currentWeatherState : StateFlow<CurrentWeatherState>
        get() = _currentWeatherState

    fun getCurrentWeather(lat:Double,lon:Double){

        getCurrentWeatherUseCase(lat,lon).onEach {res->
            when(res){
                is Resource.Error -> {
                    _currentWeatherState.value = CurrentWeatherState(errorMsg = res.msg)
                }
                is Resource.Loading -> {
                    _currentWeatherState.value = CurrentWeatherState(isLoading = true)
                }
                is Resource.Success -> {
                    _currentWeatherState.value = CurrentWeatherState(currentWeatherModel = res.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}