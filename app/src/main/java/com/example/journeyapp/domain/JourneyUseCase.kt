package com.example.journeyapp.domain

import com.example.journeyapp.data.JourneyRepository
import com.example.journeyapp.utils.mapToViewJourney
import javax.inject.Inject

class JourneyUseCase @Inject constructor(
    private val journeyRepository: JourneyRepository,
) {
    suspend fun loadData(): List<Journey> {
        val journeys = journeyRepository.loadData("data.json")
        return journeys.mapToViewJourney()
    }
}
