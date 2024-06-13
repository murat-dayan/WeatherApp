package com.muratdayan.weather.data.remote.mappers

import com.muratdayan.weather.data.remote.dto.CityDto
import com.muratdayan.weather.data.remote.dto.CurrentWeatherResponseDto
import com.muratdayan.weather.data.remote.dto.DailyDto
import com.muratdayan.weather.data.remote.dto.ForecastListDto
import com.muratdayan.weather.data.remote.dto.ForecastResponseDto
import com.muratdayan.weather.data.remote.dto.MainDto
import com.muratdayan.weather.data.remote.dto.WeatherDto
import com.muratdayan.weather.domain.models.CityForecastModel
import com.muratdayan.weather.domain.models.CurrentWeatherModel
import com.muratdayan.weather.domain.models.DailyModel
import com.muratdayan.weather.domain.models.ForecastModel
import com.muratdayan.weather.domain.models.ListForecastModel
import com.muratdayan.weather.domain.models.MainModel
import com.muratdayan.weather.domain.models.WeatherModel

// it is an extension function for CurrentWeatherResponseDto
// it converts CurrentWeatherResponseDto to CurrentWeatherModel object to use in the domain layer
fun CurrentWeatherResponseDto.toCurrentWeatherModel(): CurrentWeatherModel{
    return CurrentWeatherModel(
        base = base,
        cod = cod,
        dt = dt,
        id = id,
        name = name,
        timezone = timezone,
        visibility=visibility,
        main = main.toMainModel(),
        weather = weather.map { it.toWeatherModel() },
    )
}
// if the API response will change, it is enough to change the mappers in the data layer and the domain layer will not be affected.
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



fun WeatherDto.toWeatherModel() : WeatherModel{
    return WeatherModel(
        id = id,
        main = main,
        description = description,
        icon = icon
    )
}


fun CityDto.toCityForecastModel(): CityForecastModel{
    return CityForecastModel(
        id = id,
        name = name,
        country = country,
        sunrise = sunrise,
        sunset = sunset
    )
}


fun ForecastListDto.toListForecastModel(): ListForecastModel{
    return  ListForecastModel(
        main = main.toMainModel(),
        weatherList = weather.map { it.toWeatherModel() },
        dtTxt = dt_txt
    )
}


fun ForecastResponseDto.toForecastModel() : ForecastModel{
    return ForecastModel(
        city = city.toCityForecastModel(),
        forecastList = list.map { it.toListForecastModel() }

    )
}

fun DailyDto.toDailyModel(): DailyModel {
    return  DailyModel(
        temperatures = temperature_2m_max,
        time = time,
        uv_index_max = uv_index_max
    )
}
