package com.muratdayan.cities.data.remote.model

data class MetaDto(
    val currentPage: Int,
    val firstPage: Int,
    val lastPage: Int,
    val perPage: Int,
    val total: Int
)