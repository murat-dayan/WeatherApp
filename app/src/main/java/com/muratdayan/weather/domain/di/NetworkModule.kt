package com.muratdayan.weather.domain.di

import com.muratdayan.weather.core.utils.Constants
import com.muratdayan.weather.data.remote.services.IWeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit= Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideIWeatherService(retrofit: Retrofit): IWeatherService {
        return retrofit.create(IWeatherService::class.java)
    }


}