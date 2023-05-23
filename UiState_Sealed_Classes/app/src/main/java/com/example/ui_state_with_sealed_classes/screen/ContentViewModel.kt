package com.example.ui_state_with_sealed_classes.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ui_state_with_sealed_classes.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ContentViewModel @Inject constructor(

) : ViewModel(){
    private val _uiState = MutableStateFlow<AlbumState>(AlbumState.Loading) // uiState의 처음은 Loading으로 시작 -> LoadingScreen이 보여짐.
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _uiState.value = AlbumState.Success(MockData) // uiState를 Success로 바꿔줌 -> ReadyScreen이 보여짐.
        }
    }
}

val MockData = listOf(
    Album(
        imageId = R.drawable.img1,
        title = "테스트 이미지 1",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor in venilamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    Album(
        imageId = R.drawable.img2,
        title = "테스트 이미지 2",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magn "
    ),
    Album(
        imageId = R.drawable.img3,
        title = "테스트 이미지 3",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed diqua. Ut enim ad minim veniam, quis consequat. "
    )
)
