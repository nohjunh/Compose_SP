package com.example.authenticationdemo.network.api

import com.example.authenticationdemo.network.ApiResult
import com.example.authenticationdemo.network.models.QuestionItem
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface TestApiService {
    @GET("world.json")
    suspend fun getAllQuestions(): ApiResult<List<QuestionItem>>

}