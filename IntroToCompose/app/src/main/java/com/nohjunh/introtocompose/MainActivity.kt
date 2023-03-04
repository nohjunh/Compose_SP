package com.nohjunh.introtocompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nohjunh.introtocompose.ui.theme.IntroToComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 실제 View로 보여줄 컨텐츠를 설정
        setContent {
            IntroToComposeTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = Color(0xFF546E7A)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "$100", style = TextStyle(
                color = Color.White,
                fontSize = 35.sp,
                fontWeight = FontWeight.ExtraBold
            ))
            Spacer(modifier = Modifier.height(130.dp))
            CreateCircle()
        }

    }
}

@Preview
@Composable
fun CreateCircle() {
    var moneyCounter by remember {
        mutableStateOf(0)
    }
    // Card의 패딩은 3dp
    Card(modifier = Modifier
        .padding(3.dp)
        // 높이, 너비 모두 45dp
        .size(105.dp)
        .clickable {
            moneyCounter += 1
            Log.d("Counter", "CreateCircle: $moneyCounter")
        },
        shape = CircleShape,
        elevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "Tap $moneyCounter", modifier = Modifier)
        }
    }
}

// Composable 어노테이션은
// 컴파일러에게 컴포저블임을 알려주는 키워드의 역할을 함.
// 이는 사용자 인터페이스(UI)의 일부를 구성해야 함을 의미함.
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IntroToComposeTheme {
        // Composalble 내부에서만 Composable을 호출할 수 있다.
        MyApp()
    }
}