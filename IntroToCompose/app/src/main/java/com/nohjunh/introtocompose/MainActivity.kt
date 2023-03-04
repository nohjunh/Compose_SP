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
    val moneyCounter = remember {
        mutableStateOf(0)
    }

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
            Text(text = "$${moneyCounter.value}", style = TextStyle(
                color = Color.White,
                fontSize = 35.sp,
                fontWeight = FontWeight.ExtraBold
            ))
            Spacer(modifier = Modifier.height(130.dp))
            /*
            람다식인
            { moneyCounter.value = it +1 }은 '(Int) -> Unit' 타입의 함수
            이 람다식이 updateMoneyCounter 함수를 대체할 수 있게 되는 것이다.
            따라서, CreateCircle 함수가 호출될 때,
            첫 번째 파라미터 moneyCounter는 moneyCounter.value로 할당되며,
            두 번째 파라미터 updateMoneyCounter는 람다식 { moneyCounter.value = it + 1 }로 할당된다.
            CreateCircle 함수 내에서 updateMoneyCounter를 호출하면
            람다식 { moneyCounter.value = it + 1 }이 실행되는 원리.
             */
            CreateCircle(moneyCounter = moneyCounter.value) { newCounter ->
                moneyCounter.value = newCounter
            }
            if (moneyCounter.value > 25) {
                Text("Lots of Money !")
            }
        }
    }
}

//@Preview
@Composable
// updateMoneyCounter는 Int타입의 값을 받아서 반환값이 없는 함수
fun CreateCircle(moneyCounter: Int = 0,
                 updateMoneyCounter: (Int) -> Unit) {

    // Card의 패딩은 3dp
    Card(modifier = Modifier
        .padding(3.dp)
        // 높이, 너비 모두 45dp
        .size(105.dp)
        .clickable {
            // moneyCounter.value += 1
            updateMoneyCounter(moneyCounter + 1)
            Log.d("Counter", "CreateCircle: $moneyCounter")
        },
        shape = CircleShape,
        elevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "Tap", modifier = Modifier)
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