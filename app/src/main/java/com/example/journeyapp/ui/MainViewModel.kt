package com.example.journeyapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journeyapp.data.JourneyRepository
import com.example.journeyapp.utils.mapToViewJourney
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val journeyRepository: JourneyRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<List<Journey>>>(Result.Loading)
    val uiState: StateFlow<Result<List<Journey>>> = _uiState

    init {
        viewModelScope.launch {
            try {
                val journeys = journeyRepository.loadData("data.json")
                _uiState.value = Result.Success(journeys.mapToViewJourney())
            } catch (_: FileNotFoundException) {
                _uiState.value = Result.Error("Error loading journeys")
            }
        }
    }
}
