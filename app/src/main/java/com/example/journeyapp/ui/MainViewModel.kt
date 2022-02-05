package com.example.journeyapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journeyapp.data.JourneyRepository
import com.example.journeyapp.utils.JourneyMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val journeyRepository: JourneyRepository,
    private val journeyMapper: JourneyMapper,
) : ViewModel() {

    init {
        viewModelScope.launch {
            val journeys = journeyRepository.loadData("data.json")
            journeyMapper.mapToViewJourney(journeys)
        }
    }
}
