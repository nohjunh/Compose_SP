package com.example.authenticationdemo.data

import com.example.authenticationdemo.network.ApiResult
import com.example.authenticationdemo.network.api.TestApiService
import com.example.authenticationdemo.network.models.QuestionItem
import javax.inject.Inject

class TestDataSource @Inject constructor(
    private val testApi: TestApiService
) {
    suspend operator fun invoke(): ApiResult<List<QuestionItem>> =
        testApi.getAllQuestions()
}