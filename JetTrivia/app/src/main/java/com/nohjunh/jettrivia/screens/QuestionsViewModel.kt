package com.nohjunh.jettrivia.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjunh.jettrivia.data.DataOrException
import com.nohjunh.jettrivia.model.QuestionItem
import com.nohjunh.jettrivia.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val repository: QuestionRepository
): ViewModel() {
    val data: MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>>
        = mutableStateOf(
            DataOrException(null, true, Exception(""))
        )

    init {
        // 처음 실행 시 question이 보이도록 하기 위함.
        getAllQuestions()
    }

    private fun getAllQuestions() = viewModelScope.launch {
        data.value.loading = true
        data.value = repository.getAllQuestions()
        if (data.value.data.toString().isNotEmpty()) {
            data.value.loading = false
        }
    }

    fun getTotalQuestionCount(): Int {
        return data.value.data?.toMutableList()?.size!!
    }

}