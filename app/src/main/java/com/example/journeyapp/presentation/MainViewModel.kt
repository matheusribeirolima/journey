package com.example.journeyapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journeyapp.domain.Journey
import com.example.journeyapp.domain.JourneyUseCase
import com.example.journeyapp.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val journeyUseCase: JourneyUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<List<Journey>>>(Result.Loading)
    val uiState: StateFlow<Result<List<Journey>>> = _uiState

    init {
        viewModelScope.launch {
            try {
                val journeys = journeyUseCase.loadData()
                _uiState.value = Result.Success(journeys)
            } catch (_: FileNotFoundException) {
                _uiState.value = Result.Error("Error loading journeys")
            }
        }
    }
}
