package com.muratdayan.common.data.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muratdayan.common.data.local.entity.CityEntity

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city:CityEntity)

    @Query("SELECT*FROM cities")
    suspend fun getAllCities():List<CityEntity>
}