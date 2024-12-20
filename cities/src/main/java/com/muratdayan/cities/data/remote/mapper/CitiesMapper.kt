package com.muratdayan.cities.data.remote.mapper

import com.muratdayan.cities.data.remote.model.CityDto
import com.muratdayan.cities.data.remote.model.CityResponseDto
import com.muratdayan.cities.data.remote.model.CountryDto
import com.muratdayan.cities.domain.model.CityModel
import com.muratdayan.cities.domain.model.CityResponseModel
import com.muratdayan.cities.domain.model.CountryModel
import com.muratdayan.common.data.local.entity.CityEntity

fun CountryDto.toCountryModel() : CountryModel{
    return CountryModel(
        name = this.name
    )
}


fun CityDto.toCityModel() : CityModel{
    return CityModel(
        country = this.country.toCountryModel(),
        latitude = this.latitude,
        longitude = this.longitude,
        name = this.name
    )
}

fun CityResponseDto.toCityResponseModel() : CityResponseModel{
    return CityResponseModel(
        cities = this.cities.map { it.toCityModel() }
    )
}

fun CityModel.toCityEntity() : CityEntity {
    return CityEntity(
        id =0,
        name= this.name,
        lat = this.latitude.toDouble(),
        lon = this.longitude.toDouble(),
    )
}