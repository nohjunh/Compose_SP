package com.nohjunh.jetweatherforecast.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.nohjunh.jetweatherforecast.data.DataOrException
import com.nohjunh.jetweatherforecast.model.Weather
import com.nohjunh.jetweatherforecast.utils.formatDate
import com.nohjunh.jetweatherforecast.utils.formatDecimals
import com.nohjunh.jetweatherforecast.widgets.WeatherAppBar
import timber.log.Timber

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
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
        MainScaffold(weather = weatherData.data!!, navController)
    }
}

/*
  Scaffold는 앱의 메인 컨텐츠를 표시하는 content와
  앱 바를 포함한 TopAppBar과 BottomNavigation 등의 컴포넌트를 제공
 */
@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = weather.city.name + ", ${weather.city
                    .country}",
                navController = navController,
                elevation = 5.dp
            ) {
                Timber.tag("AppBar").e("Main Scaffold: Button Clicked")
            }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            MainContent(data = weather)
        }
    }
}

@Composable
fun MainContent(data: Weather) {
    val weatherItem = data.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"
    Timber.tag("ImgUrl").e(weatherItem.weather[0].icon)

    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(weatherItem.dt), // Wed Nov 30 처럼 출력됨.
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )
        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageUrl = imageUrl)
                Text(
                    text = formatDecimals(weatherItem.temp.day) + " 도",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = weatherItem.weather[0].main,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build(),
    )
    if (painter.state is AsyncImagePainter.State.Loading ||
        painter.state is AsyncImagePainter.State.Error
    ) {
        CircularProgressIndicator()
    }
    Image(
        painter = painter,
        contentDescription = "icon img",
        modifier = Modifier.size(80.dp)
    )
}
