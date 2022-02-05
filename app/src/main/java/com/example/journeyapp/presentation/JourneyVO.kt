package com.example.journeyapp.presentation

data class JourneyVO(
    val title: String,
    val subtitle: String,
    val day: Int,
    val literalDay: String,
)

data class JourneyCollectionVO(
    val circleColor: Int,
    val journeys: List<JourneyVO>,
)