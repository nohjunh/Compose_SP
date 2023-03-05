package com.nohjunh.movieapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.Coil
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.nohjunh.movieapp.model.Movie
import com.nohjunh.movieapp.model.getMovies

@Preview
@Composable
fun MovieRow(movie : Movie = getMovies()[0],
             onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .size(Size.ORIGINAL)
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build(),
                )
                if (painter.state is AsyncImagePainter.State.Loading ||
                    painter.state is AsyncImagePainter.State.Error
                ) {
                    CircularProgressIndicator ()
                }
                Image(
                    painter = painter,
                    contentDescription = "Movie Poster"
                )
                //Icon(imageVector = Icons.Default.AccountBox,
                //    contentDescription = "Movie Image")
            }
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = movie.title,
                     style = MaterialTheme.typography.h6)
                Text(text = "Director : ${movie.director}",
                    style = MaterialTheme.typography.caption)
                Text(text = "Released : ${movie.year}",
                    style = MaterialTheme.typography.caption)
            }
        }
    }
}