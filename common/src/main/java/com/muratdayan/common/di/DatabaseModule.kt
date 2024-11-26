package com.muratdayan.common.di

import android.content.Context
import androidx.room.Room
import com.muratdayan.common.data.local.service.CityDao
import com.muratdayan.common.data.local.service.CityDatabase
import com.muratdayan.common.data.repository.CitiesRepositoryImpl
import com.muratdayan.common.domain.repository.CitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context) : CityDatabase{
        return Room.databaseBuilder(
            context,
            CityDatabase::class.java,
            "city_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCityDao(database: CityDatabase): CityDao{
        return  database.cityDao()
    }

    @Provides
    @Singleton
    fun provideCityRepository(cityDao: CityDao) : CitiesRepository {
        return CitiesRepositoryImpl(cityDao)
    }

}