package com.example.authenticationdemo.domain

import com.example.authenticationdemo.network.ApiResult
import com.example.authenticationdemo.network.models.QuestionItem
import javax.inject.Inject

class TestUseCase @Inject constructor(
    private val testRepository: TestRepository
) {
    suspend fun getAllQuestions(): ApiResult<List<QuestionItem>> =
        testRepository.getAllQuestions()
}