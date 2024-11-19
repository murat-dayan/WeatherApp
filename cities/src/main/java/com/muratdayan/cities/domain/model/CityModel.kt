package com.muratdayan.cities.domain.model

import com.muratdayan.cities.data.remote.model.CountryDto

data class CityModel(
    val country: CountryModel,
    val latitude: String,
    val longitude: String,
    val name: String,
)
