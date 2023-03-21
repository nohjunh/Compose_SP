package com.nohjunh.jetweatherforecast.di

import com.nohjunh.jetweatherforecast.data.WeatherDatabase
import com.nohjunh.jetweatherforecast.repository.WeatherDbRepository
import com.nohjunh.jetweatherforecast.repository.WeatherDbRepositoryImpl
import com.nohjunh.jetweatherforecast.repository.WeatherRepository
import com.nohjunh.jetweatherforecast.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
    interface인 Repository를 주입하기 위한
    Repository Module 작성
*/

// WeatherRepository는 interface이기 때문에
// @Binds를 사용해서 Hilt가 WeatherRepository 의존성 객체를
// 생성할 수 있도록 정보를 제공해줌
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindWeatherRepository(
        WeatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

    @Singleton
    @Binds
    abstract fun bindWeatherDbRepository(
        WeatherDbRepositoryImpl: WeatherDbRepositoryImpl
    ): WeatherDbRepository

}