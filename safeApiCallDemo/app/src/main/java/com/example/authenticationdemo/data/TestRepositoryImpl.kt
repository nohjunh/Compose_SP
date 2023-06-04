package com.example.authenticationdemo.data

import com.example.authenticationdemo.domain.TestRepository
import com.example.authenticationdemo.network.ApiResult
import com.example.authenticationdemo.network.models.QuestionItem
import com.example.authenticationdemo.network.onSuccess
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val testDataSource: TestDataSource
): TestRepository {
    override suspend fun getAllQuestions(): ApiResult<List<QuestionItem>> {
        return testDataSource.invoke()
    }
}