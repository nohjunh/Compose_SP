package com.nohjunh.jettrivia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.nohjunh.jettrivia.screens.QuestionsViewModel
import com.nohjunh.jettrivia.ui.theme.JetTriviaTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTriviaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TriviaHome()
                }
            }
        }
    }
}

@Composable
fun TriviaHome(
    viewModel: QuestionsViewModel = hiltViewModel()
) {
    Questions(viewModel = viewModel)
}

@Composable
fun Questions(
    viewModel: QuestionsViewModel
) {
    val questions = viewModel.data.value.data?.toMutableList()
    Timber.tag("Questions size").d("${questions?.size}")
    if (viewModel.data.value.loading == true) {
        Timber.tag("Loading").d("Questions Data loading..")
    }else {
        Timber.tag("Loading").d("Questions Loading Finished..")
        questions?.forEach { questionItem ->
            Timber.tag("Result").d("Question: ${questionItem.question}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetTriviaTheme {

    }
}