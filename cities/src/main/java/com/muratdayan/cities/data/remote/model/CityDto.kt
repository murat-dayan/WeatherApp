package com.muratdayan.cities.data.remote.model

data class CityDto(
    val code: String,
    val continent: ContinentDto,
    val country: CountryDto,
    val county: CountyDto,
    val latitude: String,
    val longitude: String,
    val name: String,
    val postcode: String,
    val state: StateDto
)