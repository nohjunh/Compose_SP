package com.nohjunh.movieapp.screens.details

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nohjunh.movieapp.model.getMovies
import com.nohjunh.movieapp.widgets.MovieRow

@Composable
fun DetailsScreen(navController: NavController,
                  movieId: String?) {
    // getMovies() 에서 movie 데이터를 각각 비교해서 같은 것 가져옴
    val newMovieList = getMovies().filter { movie ->
        movie.id == movieId
    }
    Scaffold(
        topBar = {
            TopAppBar (
                backgroundColor = Color.Transparent,
                elevation = 0.dp) {
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()}
                    )
                    Spacer(modifier = Modifier.width(100.dp) )
                    Text(text = "Movies")
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) { }
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MovieRow(movie = newMovieList.first())
                Text(
                    text = newMovieList[0].title,
                    style = MaterialTheme.typography.h5
                )
            }

        }
    }
}