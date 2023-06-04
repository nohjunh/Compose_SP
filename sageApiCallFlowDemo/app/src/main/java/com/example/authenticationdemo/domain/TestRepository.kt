package com.example.authenticationdemo.domain

import com.example.authenticationdemo.network.ApiResponse
import com.example.authenticationdemo.network.ApiResult
import com.example.authenticationdemo.network.models.QuestionItem
import kotlinx.coroutines.flow.Flow

interface TestRepository {
    suspend fun getAllQuestions(): Flow<ApiResponse<List<QuestionItem>>>
}