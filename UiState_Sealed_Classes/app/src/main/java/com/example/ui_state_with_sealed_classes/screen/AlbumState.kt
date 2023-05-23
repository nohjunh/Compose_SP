package com.example.ui_state_with_sealed_classes.screen

sealed class AlbumState {
    object Loading : AlbumState()
    data class Success(
        val album: List<Album>
    ) : AlbumState()
    object Error : AlbumState()
}