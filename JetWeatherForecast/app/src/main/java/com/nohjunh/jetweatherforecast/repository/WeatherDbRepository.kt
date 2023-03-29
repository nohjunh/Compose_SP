package com.nohjunh.jetweatherforecast.repository

import com.nohjunh.jetweatherforecast.model.Favorite
import kotlinx.coroutines.flow.Flow
import com.nohjunh.jetweatherforecast.model.Units

interface WeatherDbRepository {

    fun getFavorites(): Flow<List<Favorite>>
    suspend fun insertFavorite(favorite: Favorite)
    suspend fun updateFavorite(favorite: Favorite)
    suspend fun deleteAllFavorite()
    suspend fun deleteFavorite(favorite: Favorite)
    suspend fun getFavById(city: String): Favorite


    fun getUnits(): Flow<List<Units>>
    suspend fun insertUnit(unit: Units)
    suspend fun updateUnit(unit: Units)
    suspend fun deleteAllUnits()
    suspend fun deleteUnit(unit: Units)

}