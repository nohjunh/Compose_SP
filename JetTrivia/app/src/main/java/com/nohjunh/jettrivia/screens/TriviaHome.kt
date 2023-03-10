package com.nohjunh.jettrivia.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.nohjunh.jettrivia.component.QuestionDisplay
import com.nohjunh.jettrivia.component.Questions

@Composable
fun TriviaHome(
    viewModel: QuestionsViewModel = hiltViewModel()
) {
    Questions(viewModel = viewModel)
}