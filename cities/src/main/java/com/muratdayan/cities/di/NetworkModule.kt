package com.muratdayan.cities.di

import com.muratdayan.cities.core.utils.Constants
import com.muratdayan.cities.data.remote.repository.CitiesRepositoryImpl
import com.muratdayan.cities.data.remote.service.CitiesService
import com.muratdayan.cities.domain.repository.CitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.CITIES_BASE_URI)
        .build()

    @Provides
    @Singleton
    fun provideCitiesService(retrofit: Retrofit): CitiesService {
        return retrofit.create(CitiesService::class.java)
    }

    @Provides
    @Singleton
    fun provideCitiesRepository(citiesService: CitiesService): CitiesRepository {
        return CitiesRepositoryImpl(citiesService)
    }

}