package com.nohjunh.jetweatherforecast.repository

import com.nohjunh.jetweatherforecast.data.WeatherDatabase
import com.nohjunh.jetweatherforecast.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton
import com.nohjunh.jetweatherforecast.model.Units

@Singleton
class WeatherDbRepositoryImpl @Inject constructor(
  private val db: WeatherDatabase
): WeatherDbRepository {

  override fun getFavorites(): Flow<List<Favorite>> = db.weatherDao().getFavorites()
  override suspend fun insertFavorite(favorite: Favorite) = db.weatherDao().insertFavorite(favorite)
  override suspend fun updateFavorite(favorite: Favorite) = db.weatherDao().updateFavorite(favorite)
  override suspend fun deleteAllFavorite() = db.weatherDao().deleteAllFavorite()
  override suspend fun deleteFavorite(favorite: Favorite) = db.weatherDao().deleteFavorite(favorite)
  override suspend fun getFavById(city: String): Favorite = db.weatherDao().getFavById(city)

  override fun getUnits(): Flow<List<Units>> = db.weatherDao().getUnits()

  override suspend fun insertUnit(unit: Units) = db.weatherDao().insertUnit(unit)

  override suspend fun updateUnit(unit: Units) = db.weatherDao().updateUnit(unit)

  override suspend fun deleteAllUnits() = db.weatherDao().deleteAllUnits()

  override suspend fun deleteUnit(unit: Units) = db.weatherDao().deleteUnit(unit)
}
