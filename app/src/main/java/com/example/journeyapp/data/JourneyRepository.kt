package com.example.journeyapp.data

interface JourneyRepository {

    suspend fun loadData(fileName: String): List<JourneyDTO>
}
