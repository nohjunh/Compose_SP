package com.nohjunh.jetweatherforecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.nohjunh.jetweatherforecast.R
import com.nohjunh.jetweatherforecast.model.WeatherItem
import com.nohjunh.jetweatherforecast.utils.formatDate
import com.nohjunh.jetweatherforecast.utils.formatDateTime
import com.nohjunh.jetweatherforecast.utils.formatDecimals

@Composable
fun WeatherLazyRow(daysData: List<WeatherItem>) {
    LazyColumn(
        modifier = Modifier.padding(2.dp),
        contentPadding = PaddingValues(1.dp)
    ) {
        items(items = daysData) { item: WeatherItem ->
            WeatherDetailRow(weather = item)
        }
    }
}

@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    // 날짜
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatDate(weather.dt)
                    .split(",")[0],
                modifier = Modifier.padding(start = 25.dp)
            )
            // Img
            val weatherImgUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
            WeatherStateImage(imageUrl = weatherImgUrl)
            // 상세 날씨
            Surface(
                modifier = Modifier
                    .padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(
                    text = weather.weather[0].description,
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp)
                )
            }
            // 최고/최저기온
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue.copy(alpha= 0.7f),
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(formatDecimals(weather.temp.max) +"도 ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.LightGray.copy(alpha= 0.7f),
                            fontWeight = FontWeight.Medium,
                        )
                    ) {
                        append(formatDecimals(weather.temp.min) +"도  ")
                    }
                }
            )
            //// Text End
        }
    }
}

@Composable
fun SunsetSunRiseRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 6.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise icon",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = " " + formatDateTime(weather.sunrise),
                style = MaterialTheme.typography.caption
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Text(
                text = formatDateTime(weather.sunset) + " ",
                style = MaterialTheme.typography.caption
            )
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunset icon",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.humidity}%",
                style = MaterialTheme.typography.caption
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.pressure} psi",
                style = MaterialTheme.typography.caption
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.speed}%",
                style = MaterialTheme.typography.caption
            )
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
