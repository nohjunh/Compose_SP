package com.nohjunh.introtocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjunh.introtocompose.ui.theme.IntroToComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 실제 View로 보여줄 컨텐츠를 설정
        setContent {
            IntroToComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxHeight()
                        .fillMaxWidth()
                        .padding(all = 59.dp),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

// Composable 어노테이션은
// 컴파일러에게 컴포저블임을 알려주는 키워드의 역할을 함.
// 이는 사용자 인터페이스(UI)의 일부를 구성해야 함을 의미함.
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun ShowAge(age : Int = 12) {
    Text(text = age.toString())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IntroToComposeTheme {
        // Composalble 내부에서만 Composable을 호출할 수 있다.
        Column() {
            Greeting("QQQ")
            ShowAge(age = 30)
        }
    }
}