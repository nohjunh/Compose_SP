package com.nohjunh.jetweatherforecast.repository

import com.nohjunh.jetweatherforecast.data.DataOrException
import com.nohjunh.jetweatherforecast.model.Weather
import com.nohjunh.jetweatherforecast.network.WeatherApi
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {
    override suspend fun getWeather(cityQuery: String, units: String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            Timber.tag("RepoAPi").e("API call")
            api.getWeather(query = cityQuery, units = units)
        }catch (e: Exception) {
            Timber.tag("RepoEx").e("getWeather: $e")
            return DataOrException(e = e)
        }
        Timber.tag("RepoImpl").e("getWeather: $response")
        return DataOrException(data = response)
    }
}