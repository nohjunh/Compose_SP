package com.nohjunh.jetweatherforecast.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjunh.jetweatherforecast.model.Favorite
import com.nohjunh.jetweatherforecast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: WeatherDbRepository
): ViewModel() {
    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
        val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites().distinctUntilChanged()
                .collect { listOfFavs ->
                    if (listOfFavs.isEmpty()) {
                        Timber.tag("FavViewModel").e("empty favs")
                    }else {
                        _favList.value = listOfFavs
                        Timber.tag("FavViewModel_Datas").e("${favList.value}")
                    }
                }
        }
    }

    fun insertFavorite(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertFavorite(favorite)
    }
    fun updateFavorite(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateFavorite(favorite)
    }
    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteFavorite(favorite)
    }

}