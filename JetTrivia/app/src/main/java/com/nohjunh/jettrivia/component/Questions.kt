package com.nohjunh.jettrivia.component

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.nohjunh.jettrivia.screens.QuestionsViewModel
import timber.log.Timber

@Composable
fun Questions(
    viewModel: QuestionsViewModel
) {
    val questions = viewModel.data.value.data?.toMutableList()
    Timber.tag("Questions size").d("${questions?.size}")

    if (viewModel.data.value.loading == true) {
        CircularProgressIndicator()
        Timber.tag("Loading").d("Questions Data loading..")
    }else {
        Timber.tag("Loading").d("Questions Loading Finished..")
        questions?.forEach { questionItem ->
            Timber.tag("Result").d("Question: ${questionItem.question}")
        }
    }
}