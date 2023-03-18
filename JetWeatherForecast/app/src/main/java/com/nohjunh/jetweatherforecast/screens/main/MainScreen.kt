package com.nohjunh.jetweatherforecast.screens.main

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nohjunh.jetweatherforecast.data.DataOrException
import com.nohjunh.jetweatherforecast.model.Weather

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    ShowData(mainViewModel)
}

@Composable
fun ShowData(mainViewModel: MainViewModel) {
    /*
    produceState는 state나 mutableStateOf와 같은 상태 저장 솔루션과 달리,
    상태를 생성하고 업데이트하기 위해 produce-consumer 패턴을 사용. 즉 StateFlow 객체를 반환함
    상태를 업데이트할 때마다 새로운 값으로 Compose에 알림을 보내고,
    Compose는 자동으로 화면을 다시 그린다.
    */
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
        value = mainViewModel.getWeatherData(city = "Seoul", units = "metric")
     }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    }else if (weatherData.data != null) {
        Text(text= "Main Screen ${weatherData.data!!}")
    }
}