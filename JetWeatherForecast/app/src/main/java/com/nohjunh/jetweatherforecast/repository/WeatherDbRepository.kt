package com.nohjunh.jetweatherforecast.repository

import com.nohjunh.jetweatherforecast.model.Favorite
import kotlinx.coroutines.flow.Flow


interface WeatherDbRepository {

    fun getFavorites(): Flow<List<Favorite>>
    suspend fun insertFavorite(favorite: Favorite)
    suspend fun updateFavorite(favorite: Favorite)
    suspend fun deleteAllFavorite()
    suspend fun deleteFavorite(favorite: Favorite)
    suspend fun getFavById(city: String): Favorite
}