package com.example.journeyapp.domain

import com.example.journeyapp.data.JourneyRepository
import javax.inject.Inject

class JourneyUseCase @Inject constructor(
    private val journeyRepository: JourneyRepository,
    private val journeyMapper: JourneyMapper,
) {
    suspend fun loadData(): List<Journey> {
        val journeys = journeyRepository.loadData("data.json")
        return journeyMapper.mapToViewJourney(journeys)
    }
}
