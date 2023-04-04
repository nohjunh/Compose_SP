package com.example.barchart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.barchart.ui.theme.BarChartTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BarChartTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "워크플로우 타입 TOP 3")
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "월간")

                        BarChartScreen()
                    }
                }
            }
        }
    }
}

@Composable
internal fun BarChartScreen() {
    val chartColors = listOf(
        colorResource(id = R.color.bar_red),
        colorResource(id = R.color.bar_blue),
        colorResource(id = R.color.bar_green),
    )
    val defaultMaxHeight = 200.dp

    // 워크플로우 TOP 3
    val chartValues = listOf(50f, 30f, 20f)
    // val proportions = chartValues.toPercent()

    BarChart(
        inputValues = chartValues,
        colors = chartColors,
        maxHeight = defaultMaxHeight
    )
}

@Composable
internal fun BarChart(
    modifier: Modifier = Modifier,
    inputValues: List<Float>,
    colors: List<Color>,
    maxHeight: Dp
) {
    assert(inputValues.isNotEmpty()) { "Input values are empty" }

    val borderColor = MaterialTheme.colors.primary
    val density = LocalDensity.current
    val strokeWidth = with(density) { 1.dp.toPx() }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier.then(
                Modifier
                    .fillMaxWidth()
                    .height(maxHeight)
                    .drawBehind {
                        // draw X축
                        drawLine(
                            color = borderColor,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = strokeWidth
                        )
                        // draw Y축
                        drawLine(
                            color = borderColor,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = strokeWidth
                        )
                    }
            ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            inputValues.forEachIndexed { index, item ->
                Bar(
                    value = item,
                    color = colors[index],
                    maxHeight = maxHeight
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            BarInfoText("Test 1")
            BarInfoText("Test 2")
            BarInfoText("Test 3")
        }

    }

}

@Composable
private fun RowScope.Bar(
    value: Float,
    color: Color,
    maxHeight: Dp
) {
    val itemHeight = remember(value) { value * maxHeight.value / 100 }

    Spacer(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .height(itemHeight.dp)
            .weight(1f)
            .background(color) // Bar 색상
    )
}


@Composable
private fun RowScope.BarInfoText(
    value: String,
) {
    Text(
        text = value,
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .weight(1f)
    )
}