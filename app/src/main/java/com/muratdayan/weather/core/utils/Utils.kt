package com.muratdayan.weather.core.utils

import com.muratdayan.weather.R
import com.muratdayan.weather.domain.models.WeatherModel


fun WeatherModel.checkWeatherforImage() : Int{
    val iconResource = when(this.description){
        "clear sky" -> R.drawable.ic_sun
        "light rain" -> R.drawable.rainy_sunny_cloud
        "scattered clouds" -> R.drawable.scattered_clouds
        "few clouds" -> R.drawable.scattered_clouds
        "broken clouds" -> R.drawable.broken_cloud
        "overcast clouds" -> R.drawable.scattered_clouds
        else -> R.drawable.scattered_clouds
    }

    return iconResource
}