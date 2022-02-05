package com.example.journeyapp.data

interface JourneyRepository {

    fun loadData(fileName: String): List<JourneyDTO>
}
