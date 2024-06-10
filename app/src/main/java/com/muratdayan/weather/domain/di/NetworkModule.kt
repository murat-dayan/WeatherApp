package com.muratdayan.weather.domain.di

import com.muratdayan.weather.core.utils.Constants
import com.muratdayan.weather.data.remote.repository.RepositoryImpl
import com.muratdayan.weather.data.remote.services.IWeatherService
import com.muratdayan.weather.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

// the module that provides the network dependencies
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // provides the retrofit instance with the base url and the gson converter factory
    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit= Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    // provides the IWeatherService instance with the retrofit instance
    @Provides
    @Singleton
    fun provideIWeatherService(retrofit: Retrofit): IWeatherService {
        return retrofit.create(IWeatherService::class.java)
    }

    // provides the WeatherRepository instance with the IWeatherService instance
    @Provides
    @Singleton
    fun provideWeatherRepository(iWeatherService: IWeatherService) : WeatherRepository{
        return RepositoryImpl(iWeatherService)
    }


}