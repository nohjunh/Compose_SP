package com.example.authenticationdemo.di

import com.example.authenticationdemo.data.TestRepositoryImpl
import com.example.authenticationdemo.domain.TestRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
    interface인 Repository를 주입하기 위한
    Repository Module 작성
*/
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindTestRepository (
        testRepositoryImpl: TestRepositoryImpl
    ): TestRepository

    /*
    @Singleton
    @Binds
    abstract fun bindAuthRepository (
        AuthRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Singleton
    @Binds
    abstract fun bindInstanceRepository (
        InstanceRepositoryImpl: InstanceRepositoryImpl
    ): InstanceRepository

    @Singleton
    @Binds
    abstract fun bindDashboardRepository (
        DashboardRepositoryImpl: DashboardRepositoryImpl
    ): DashboardRepository
     */

}