package com.example.authenticationdemo.di

import com.example.authenticationdemo.network.api.TestApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideTestApiService(retrofit: Retrofit): TestApiService {
        return retrofit.create(TestApiService::class.java)
    }
}