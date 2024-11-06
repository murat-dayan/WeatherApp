package com.muratdayan.weather.domain.di

import com.muratdayan.weather.core.utils.Constants
import com.muratdayan.weather.data.remote.repository.RepositoryImpl
import com.muratdayan.weather.data.remote.services.OpenMeteoService
import com.muratdayan.weather.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

// the module that provides the network dependencies
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



    @Provides
    @Singleton
    @Named("open_meteo_retrofit")
    fun provideOpenMeteoRetrofitInstance(): Retrofit= Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.OPEN_METEO_BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideOpenMeteoService(@Named("open_meteo_retrofit") retrofit: Retrofit): OpenMeteoService {
        return retrofit.create(OpenMeteoService::class.java)
    }



    // provides the WeatherRepository instance with the OpenWeatherService instance
    @Provides
    @Singleton
    fun provideWeatherRepository(openMeteoService: OpenMeteoService) : WeatherRepository{
        return RepositoryImpl(openMeteoService)
    }




}