package com.example.ui_state_with_sealed_classes.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ContentScreen(
    viewModel: ContentViewModel = hiltViewModel()
) {
    val albumState by viewModel.uiState.collectAsState()

    Content(albumState)
}

@Composable
fun Content(
    state: AlbumState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "뒤로 가기"
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (state) {
                is AlbumState.Loading -> LoadingScreen()
                is AlbumState.Success -> ReadyScreen(albumList = state.album)
                is AlbumState.Error -> ErrorScreen()
            }
        }
    }
}
