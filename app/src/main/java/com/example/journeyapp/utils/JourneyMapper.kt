package com.example.journeyapp.utils

import android.icu.text.MessageFormat
import com.example.journeyapp.data.JourneyDTO
import com.example.journeyapp.ui.Journey
import javax.inject.Inject

class JourneyMapper @Inject constructor(
    private val messageFormat: MessageFormat
) {
    fun mapToViewJourney(journeys: List<JourneyDTO>) = journeys.mapIndexed { index, journeyDTO ->
        Journey(
            title = journeyDTO.title,
            subtitle = journeyDTO.subtitle,
            day = journeyDTO.day.toString(),
            literalDay = getLiteralDayByNumber(journeyDTO.day),
            isDividerVisible = index < journeys.lastIndex
        )
    }

    private fun getLiteralDayByNumber(day: Int): String {
        return "day " + messageFormat.format(day)
    }
}
