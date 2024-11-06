package com.muratdayan.weather.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.weather.core.common.Resource
import com.muratdayan.weather.domain.models.CurrentWeatherModel
import com.muratdayan.weather.domain.use_cases.GetCurrentAndHourlyForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

// used to handle the business logic related to fetching and managing the current weather data
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentAndHourlyForecastUseCase: GetCurrentAndHourlyForecastUseCase
)  :ViewModel(){

    private val _currentForecastState = MutableStateFlow<Resource<CurrentWeatherModel>>(Resource.Idle())
    val currentForecastState: StateFlow<Resource<CurrentWeatherModel>> = _currentForecastState



    fun getCurrentAndHourlyForecast(lat: Double, lon: Double) {
        viewModelScope.launch {
            getCurrentAndHourlyForecastUseCase.invoke(lat = lat, lon = lon).onEach { result ->
                _currentForecastState.value = result
            }.launchIn(viewModelScope)
        }
    }

}