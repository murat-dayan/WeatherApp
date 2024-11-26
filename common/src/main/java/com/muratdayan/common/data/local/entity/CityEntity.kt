package com.muratdayan.common.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val name:String,
    val lon:Double,
    val lat:Double

)