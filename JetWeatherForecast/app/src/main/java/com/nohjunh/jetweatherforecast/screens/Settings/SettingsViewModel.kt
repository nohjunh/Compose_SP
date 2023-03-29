package com.nohjunh.jetweatherforecast.screens.Settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjunh.jetweatherforecast.model.Units
import com.nohjunh.jetweatherforecast.repository.WeatherDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val repository: WeatherDbRepository

): ViewModel() {
    private val _unitList = MutableStateFlow<List<com.nohjunh.jetweatherforecast.model.Units>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits().distinctUntilChanged()
                .collect { listOfUnits ->
                    if (listOfUnits.isEmpty()) {
                        Timber.e("Empty")
                    }else {
                        _unitList.value = listOfUnits
                    }
                }
        }
    }

    fun insertUnit(unit: Units) = viewModelScope.launch { repository.insertUnit(unit) }
    fun updateUnit(unit: Units) = viewModelScope.launch { repository.updateUnit(unit) }
    fun deleteUnit(unit: Units) = viewModelScope.launch { repository.deleteUnit(unit) }
    fun deleteAllUnits() = viewModelScope.launch { repository.deleteAllUnits() }
}
