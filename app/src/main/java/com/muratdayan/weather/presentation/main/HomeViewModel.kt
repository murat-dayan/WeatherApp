package com.muratdayan.weather.presentation.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.muratdayan.common.utils.Resource
import com.muratdayan.weather.domain.models.CurrentWeatherModel
import com.muratdayan.weather.domain.use_cases.GetCurrentAndHourlyForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _locationState = MutableStateFlow<Pair<Double, Double>?>(null)
    val locationState = _locationState.asStateFlow()

    private val _permissionStatus = MutableStateFlow(false)
    val permissionStatus = _permissionStatus.asStateFlow()

    fun checkLocationPermissions(context: Context): Boolean{
        val coarseLocation = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val fineLocation = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        _permissionStatus.value = coarseLocation && fineLocation
        return _permissionStatus.value
    }

    @SuppressLint("MissingPermission")
    fun requestLocation(context: Context){
        if (_permissionStatus.value){
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation.addOnSuccessListener { location->
                location?.let {
                    _locationState.value = Pair(it.latitude,it.longitude)
                    Log.i("HomeViewModel","${it.latitude} - ${it.longitude}")
                    getCurrentAndHourlyForecast(it.latitude,it.longitude)
                }
            }
        }
    }



    private fun getCurrentAndHourlyForecast(lat: Double, lon: Double) {
        viewModelScope.launch {
            getCurrentAndHourlyForecastUseCase.invoke(lat = lat, lon = lon).onEach { result ->
                _currentForecastState.value = result
            }.launchIn(viewModelScope)
        }
    }

   /* fun getTimeFromDateTime(dateTime: String): String {
        // Verilen stringin saat kısmını çıkarmak için substring kullanıyoruz
        return dateTime.substring(11, 16)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonthAndDayFromTimestamp(timestamp: Long): String {
        // Unix zaman damgasını LocalDateTime nesnesine çeviriyoruz
        val dateTime = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(timestamp),
            java.time.ZoneId.systemDefault()
        )

        // Ay ismini ve günü alıyoruz (Örneğin: "July, 12")
        val month = dateTime.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        val day = dateTime.get(ChronoField.DAY_OF_MONTH)

        // İngilizce olarak formatlıyoruz
        return "$month, $day"
    }*/

}