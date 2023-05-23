package com.example.ui_state_with_sealed_classes.screen

import androidx.annotation.DrawableRes

data class Album(
    @DrawableRes val imageId: Int,
    val title: String,
    val description: String,
)
