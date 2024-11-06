package com.muratdayan.weather.data.remote.mappers

import com.muratdayan.weather.data.remote.dto.CurrentDto
import com.muratdayan.weather.data.remote.dto.CurrentWeatherRespondDto
import com.muratdayan.weather.data.remote.dto.DailyDto
import com.muratdayan.weather.data.remote.dto.HourlyDto
import com.muratdayan.weather.domain.models.CurrentModel
import com.muratdayan.weather.domain.models.CurrentWeatherModel
import com.muratdayan.weather.domain.models.DailyModel
import com.muratdayan.weather.domain.models.HourlyModel


fun CurrentDto.toCurrentModel(): CurrentModel {
    return CurrentModel(
        temperature = temperature_2m.toString()
    )
}

fun HourlyDto.toHourlyModel(): HourlyModel{
    return HourlyModel(
        temperatures = temperature_2m,
        time = time
    )
}

fun CurrentWeatherRespondDto.toCurrentWeatherModel(): CurrentWeatherModel{
    return CurrentWeatherModel(
        current = current.toCurrentModel(),
        hourly = hourly.toHourlyModel()
    )
}

fun DailyDto.toDailyModel(): DailyModel {
    return  DailyModel(
        temperatures = temperature_2m_max,
        time = time,
        uv_index_max = uv_index_max
    )
}
