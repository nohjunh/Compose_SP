package com.nohjunh.tipapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjunh.tipapp.components.InputField
import com.nohjunh.tipapp.ui.theme.TipAppTheme
import com.nohjunh.tipapp.widgets.RoundIconButton
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                //TopHeader()
                MainContent()
            }
        }
    }
}

@Composable
// (content: @Composable () -> Unit) 타입 형식을 @Composable로
// 하는 것은 컴파일러에게 컴포저블로 예상이 된다는 것을 알려주기 위함.
// 아래 예시처럼, content에 전달하는 것을 Composable로 넣을 수 있다.
fun MyApp(content: @Composable () -> Unit) {
    // Theme도 Container
    TipAppTheme {
        // A surface container using the 'background' color from the theme
         Surface(
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

//@Preview
@Composable
fun TopHeader(totalPerPerson: Double = 134.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .height(150.dp)
            .clip(shape = CircleShape.copy(all = CornerSize(12.dp))),
            //.clip(shape = RoundedCornerShape(corner = CornerSize(12.dp)))
        color = Color(0xFFE9D7F7)
    ) {
        Column(modifier = Modifier
            .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val total = "%.2f".format(totalPerPerson)
            Text(text = "Total Per Person",
                style = MaterialTheme.typography.h5)
            Text(text = "$$total",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold)
        }
    }
}

/*
BillForm 함수의 onValChange 매개변수에 BillForm() { billAmt -> ... }
로 전달된 람다식이 대입된다.
이 때, 람다식의 매개변수 billAmt가 onValChange 함수의 매개변수로 전달되어
해당 함수에서 사용된다. 따라서, BillForm() 함수 안에서
onValChange 함수가 호출되면, 람다식에서 정의된
Log.d("AMT", "MainContent: $billAmt") 코드가 실행됨.
 */
@ExperimentalComposeUiApi
@Preview
@Composable
fun MainContent() {
    Column(
        modifier = Modifier
            .padding(all = 12.dp)
    ) {
        BillForm() { billAmt -> // billAmt = bill amount
            Log.d("AMT", "MainContent: $billAmt")
        }
    }
}

@Preview
@ExperimentalComposeUiApi
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    onValChange: (String) -> Unit = {}
) {
    val totalBillState = remember {
        mutableStateOf("")
    }
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    val sliderPositionState = remember {
        mutableStateOf(0f)
    }
    TopHeader()

    Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputField(
                valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValChange(totalBillState.value.trim())

                    keyboardController?.hide()
                }
            )
            //if (validState) {
                Row(
                    modifier = Modifier
                            .padding(3.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = Modifier.align(
                            alignment = Alignment.CenterVertically,
                        ),
                        text = "Split"
                    )
                    Spacer(modifier = Modifier.width(120.dp))
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        RoundIconButton(
                            imageVector = Icons.Default.Remove,
                            onClick = {Log.d("Icon", "BillForm: Removed Click")}
                        )
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 9.dp, end = 9.dp),
                            text = "2",
                        )
                        RoundIconButton(
                            imageVector = Icons.Default.Add,
                            onClick = {Log.d("Icon", "BillForm: Added Click")}
                        )
                    }
                }
                // Tip Row
                Row(
                    modifier = Modifier
                        .padding(horizontal = 3.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "Tip",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(200.dp))
                    Text(
                        text = "$33.00",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text= "33%")
                    Spacer(modifier = Modifier.height(14.dp))
                    Slider(
                        value = sliderPositionState.value,
                        onValueChange = { newVal ->
                            sliderPositionState.value = newVal
                            Log.d("slider", "BillForm : $newVal")
                        },
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp),
                        steps = 5
                    )
                }
            //}else {
                //Box() {

                //}
            //}
        }
    }
}

//@Preview(showBackground = true)
@ExperimentalComposeUiApi
@Composable
fun DefaultPreview() {
    TipAppTheme {
        MyApp {
            //TopHeader()
            MainContent()
        }
    }
}