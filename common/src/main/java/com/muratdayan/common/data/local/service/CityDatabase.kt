package com.muratdayan.common.data.local.service

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muratdayan.common.data.local.entity.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}