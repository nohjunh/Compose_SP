package com.example.authenticationdemo.screens

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authenticationdemo.domain.TestRepository
import com.example.authenticationdemo.domain.TestUseCase
import com.example.authenticationdemo.network.ApiResponse
import com.example.authenticationdemo.network.models.QuestionItem
import com.example.authenticationdemo.network.onException
import com.example.authenticationdemo.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class TestState(
    val dataSet: List<QuestionItem> = emptyList()
)

@HiltViewModel
class TestViewModel @Inject constructor(
    private val testUseCase: TestUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(TestState())
    val uiState: StateFlow<TestState> = _uiState.asStateFlow()

    init {
        getAllQuestions()
    }

    private fun getAllQuestions() = viewModelScope.launch {
        testUseCase.getAllQuestions().collect() {
            when(it) {
                is ApiResponse.Success -> {
                    Timber.tag("ViewModel").d("$it")
                    _uiState.update { state ->
                        state.copy(
                            dataSet = it.data
                        )
                    }
                }
                is ApiResponse.Failure -> {
                    Timber.tag("getAllQuestion_ApiFailure").e("${it.code} ${it.errorMessage}")
                }
                ApiResponse.Loading ->  {
                    Timber.tag("ViewModel").e("Loading")
                }
            }
        }
    }
}