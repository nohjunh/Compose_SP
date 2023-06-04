package com.example.authenticationdemo.domain

import com.example.authenticationdemo.network.ApiResult
import com.example.authenticationdemo.network.models.QuestionItem

interface TestRepository {
    suspend fun getAllQuestions(): ApiResult<List<QuestionItem>>
}