package com.example.ui_state_with_sealed_classes.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ReadyScreen(
    albumList: List<Album>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            item {
                Text(text = "Ready Screen")
            }
            items(albumList) { item ->
                AlbumItem(item = item)
            }
        }
    }
}

@Composable
fun AlbumItem(item: Album) {
    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Image(painter = painterResource(id = item.imageId), contentDescription = "image")
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.h4
            )
            Text(
                text = item.description
            )
        }
    }
}
