package com.muratdayan.weather.presentation.weather_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.utils.Resource
import com.muratdayan.weather.domain.use_cases.GetDailyForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    private val getDailyForecastUseCase: GetDailyForecastUseCase
) : ViewModel() {

    private val _dailyModelState = MutableStateFlow(DailyWeatherState())
    val dailyModelState : StateFlow<DailyWeatherState>
        get() = _dailyModelState



    fun getDailyForecastWeather(lat: Double, lon: Double){
        getDailyForecastUseCase(lat, lon).onEach {resource ->
            when(resource){
                is Resource.Error -> {
                    _dailyModelState.value = DailyWeatherState(error = resource.msg ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _dailyModelState.value = DailyWeatherState(isLoading = true)
                }
                is Resource.Success -> {
                    _dailyModelState.value = DailyWeatherState(dailymodel = resource.data)
                }

                is Resource.Idle -> {}
            }
        }.launchIn(viewModelScope)
    }



}