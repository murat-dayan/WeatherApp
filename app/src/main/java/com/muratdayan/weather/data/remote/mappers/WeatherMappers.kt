package com.muratdayan.weather.data.remote.mappers

import com.muratdayan.weather.data.remote.dto.CurrentWeatherResponseDto
import com.muratdayan.weather.data.remote.dto.MainDto
import com.muratdayan.weather.domain.models.CurrentWeatherModel
import com.muratdayan.weather.domain.models.MainModel

fun CurrentWeatherResponseDto.toCurrentWeatherModel() :CurrentWeatherModel{
    return CurrentWeatherModel(
        base = base,
        cod = cod,
        dt = dt,
        id = id,
        name = name,
        timezone = timezone,
        visibility=visibility,
        main = main.toMainModel()
    )
}

fun MainDto.toMainModel(): MainModel{
    return MainModel(
        feelsLike = feels_like,
        grndLevel = grnd_level,
        humidity = humidity,
        pressure = pressure,
        seaLevel = sea_level,
        temp = temp,
        tempMax = temp_max,
        tempMin = temp_min
    )
}
