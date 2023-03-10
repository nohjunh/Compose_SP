package com.nohjunh.jettrivia.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nohjunh.jettrivia.model.Question
import com.nohjunh.jettrivia.model.QuestionItem
import com.nohjunh.jettrivia.screens.QuestionsViewModel
import com.nohjunh.jettrivia.util.AppColors
import timber.log.Timber

@Composable
fun Questions(
    viewModel: QuestionsViewModel
) {
    val questions = viewModel.data.value.data?.toMutableList()

    val questionIndex = remember {
        mutableStateOf(0)
    }

    Timber.tag("Questions size").d("${questions?.size}")

    if (viewModel.data.value.loading == true) {
        CircularProgressIndicator()
    }else {
        val question = try {
            questions?.get(questionIndex.value)
        }catch (ex: Exception) {
            null
        }

        if (questions != null) {
            // onNextClicked은 람다식으로 전달
            QuestionDisplay(question = question!!, questionIndex = questionIndex, viewModel = viewModel) {
                questionIndex.value +=1
            }
        }
    }
}

//@Preview
@Composable
fun QuestionDisplay(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: QuestionsViewModel,
    onNextClicked: (Int) -> Unit
) {
    val choicesState = remember(question) {
        question.choices.toMutableList()
    }
    val answerState = remember(question) {
        mutableStateOf<Int?>(null)
    }
    val correctAnswerState = remember(question) {
        mutableStateOf<Boolean?>(null)
    }
    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answerState.value = it
            // 맞다면 correctAnswerState의 값은 true로 바뀔 것이다.
            correctAnswerState.value = choicesState[it] == question.answer
        }
    }

    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = AppColors.mDarkPurple
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            if (questionIndex.value >= 3) {
                ShowProgress(score = questionIndex.value)
            }
            QuestionTracker(counter = questionIndex.value, outOf = viewModel.getTotalQuestionCount())
            DrawDottedLine(pathEffect = pathEffect)

            Column {
                Text(
                    text = question.question,
                    // 부모 레이아웃의 높이의 0.3배로 설정
                    modifier = Modifier
                        .padding(6.dp)
                        .align(alignment = Alignment.Start)
                        .fillMaxHeight(0.3f),
                    fontSize = 17.sp,
                    color = AppColors.mOffWhite,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp,
                )
                // choices
                choicesState.forEachIndexed { index, answerText ->
                    Row(
                        modifier = Modifier
                            .padding(3.dp)
                            .fillMaxWidth()
                            .height(45.dp)
                            .border(
                                width = 4.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        AppColors.mOffDarkPurple,
                                        AppColors.mOffDarkPurple
                                    )
                                ),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .clip(
                                RoundedCornerShape(
                                    topStartPercent = 50,
                                    topEndPercent = 50,
                                    bottomEndPercent = 50,
                                    bottomStartPercent = 50
                                )
                            )
                            .background(Color.Transparent),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (answerState.value == index),
                            onClick = {
                                updateAnswer(index)
                            },
                            modifier = Modifier
                                .padding(start = 16.dp),
                            colors = RadioButtonDefaults.colors(
                                selectedColor =
                                if (correctAnswerState.value == true && index == answerState.value) {
                                    Color.Green.copy(alpha = 0.2f)
                                }else {
                                    Color.Red.copy(alpha = 0.2f)
                                }
                            )
                        ) // end radiobutton
                        val annotatedString = buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                fontWeight = FontWeight.Light,
                                color = if (correctAnswerState.value == true && index == answerState.value) {
                                    Color.Green
                                }else if(correctAnswerState.value == false && index == answerState.value){
                                    Color.Red
                                }else {
                                    AppColors.mOffWhite
                                },
                                fontSize = 17.sp
                            )) {
                                append(text = answerText)
                            }
                        }
                        Text(
                            text = annotatedString,
                            modifier = Modifier
                                .padding(6.dp)
                        )
                    }
                } // end choices
                Button(
                    onClick = {
                        onNextClicked(questionIndex.value)
                    },
                    modifier = Modifier
                        .padding(3.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = AppColors.mLightBlue
                    )
                ) {
                    Text(
                        text = "Next",
                        modifier = Modifier
                            .padding(4.dp),
                        color = AppColors.mOffWhite,
                        fontSize = 17.sp
                    )
                } // end button
            }
        }
    }
}

@Composable
fun QuestionTracker(
    counter: Int = 10,
    outOf: Int = 100
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
                withStyle(style = SpanStyle(color = AppColors.mLightGray,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 27.sp
                )) {
                    append("Question $counter/")
                    withStyle(style = SpanStyle(color = AppColors.mLightGray,
                                                fontWeight = FontWeight.Light,
                                                fontSize = 14.sp
                    )) {
                        append("$outOf")
                    }
                }
            }
        },
        modifier = Modifier
            .padding(20.dp)
    )
}

@Composable
fun DrawDottedLine(pathEffect: PathEffect) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        drawLine(
            color = AppColors.mLightGray,
            start = Offset(0f, 0f),
            end = Offset(size.width, y= 0f),
            pathEffect = pathEffect
        )
    }
}

@Composable
fun ShowProgress(score: Int = 12) {
    val gradient = Brush.linearGradient(listOf(Color(0xFFF95075),
                                              Color(0xFFBE6BE5)))

    val progressFactor by remember(score) {
        mutableStateOf(score*0.005f)
    }

    Row(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(45.dp)
            .border(
                width = 4.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        AppColors.mLightPurple, AppColors.mLightPurple
                    )
                ),
                shape = RoundedCornerShape(34.dp)
            )
            .clip(
                RoundedCornerShape(
                    topStartPercent = 50,
                    topEndPercent = 50,
                    bottomEndPercent = 50,
                    bottomStartPercent = 50
                )
            )
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 버튼의 너비를 이용해 게이지가 차는 것을 표현함.
        // fillMaxWidth(0.3f)라면 30% 너비.
        Button(
            contentPadding = PaddingValues(1.dp),
            onClick = { },
            modifier = Modifier
                .fillMaxWidth(progressFactor)
                .background(brush = gradient),
            enabled = false,
            elevation = null,
            colors = buttonColors(
                backgroundColor = Color.Transparent,
                disabledBackgroundColor = Color.Transparent
            )
        ) {
            Text(
                text = (score*10).toString(),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(23.dp))
                    .fillMaxWidth()
                    .fillMaxHeight(0.87f)
                    .padding(6.dp),
                color = AppColors.mOffWhite,
                textAlign = TextAlign.Center
            )
        } // end button

    }
}