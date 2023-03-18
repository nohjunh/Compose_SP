package com.nohjunh.jetweatherforecast.repository

import com.nohjunh.jetweatherforecast.data.DataOrException
import com.nohjunh.jetweatherforecast.model.Weather

interface WeatherRepository {
    suspend fun getWeather(cityQuery: String, units: String): DataOrException<Weather, Boolean, Exception>
}