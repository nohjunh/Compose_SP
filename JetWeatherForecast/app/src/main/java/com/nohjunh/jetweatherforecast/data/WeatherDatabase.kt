package com.nohjunh.jetweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nohjunh.jetweatherforecast.model.Favorite

@Database(entities = [Favorite::class, com.nohjunh.jetweatherforecast.model.Units::class], version = 2, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

}