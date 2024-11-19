package com.muratdayan.cities.data.remote.model

data class CityResponseDto(
    val cities: List<CityDto>,
    val meta: MetaDto
)