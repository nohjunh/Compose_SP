package com.nohjunh.jettrivia.repository

import com.nohjunh.jettrivia.data.DataOrException
import com.nohjunh.jettrivia.model.QuestionItem

interface QuestionRepository {

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception>
}