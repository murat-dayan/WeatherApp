package com.muratdayan.weather.domain.di

import com.muratdayan.weather.core.utils.Constants
import com.muratdayan.weather.data.remote.repository.RepositoryImpl
import com.muratdayan.weather.data.remote.services.OpenMeteoService
import com.muratdayan.weather.data.remote.services.OpenWeatherService
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

    // provides the retrofit instance with the base url and the gson converter factory
    @Provides
    @Singleton
    @Named("open_weather_retrofit")
    fun provideOpenWeatherRetrofitInstance(): Retrofit= Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.OPEN_WEATHER_BASE_URL)
        .build()

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

    // provides the OpenWeatherService instance with the retrofit instance
    @Provides
    @Singleton
    fun provideOpenWeatherService(@Named("open_weather_retrofit") retrofit: Retrofit): OpenWeatherService {
        return retrofit.create(OpenWeatherService::class.java)
    }

    // provides the WeatherRepository instance with the OpenWeatherService instance
    @Provides
    @Singleton
    fun provideWeatherRepository(openWeatherService: OpenWeatherService, openMeteoService: OpenMeteoService) : WeatherRepository{
        return RepositoryImpl(openWeatherService,openMeteoService)
    }




}