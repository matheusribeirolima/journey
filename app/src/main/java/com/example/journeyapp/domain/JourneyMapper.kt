package com.example.journeyapp.domain

import android.icu.text.MessageFormat
import com.example.journeyapp.data.JourneyDTO
import javax.inject.Inject

class JourneyMapper @Inject constructor(
    private val messageFormat: MessageFormat,
) {
    fun mapToViewJourney(journeys: List<JourneyDTO>) = journeys.mapIndexed { index, journeyDTO ->
        val day = journeyDTO.day + 1
        Journey(
            title = journeyDTO.title,
            subtitle = journeyDTO.subtitle,
            day = day.toString(),
            literalDay = getLiteralDayByNumber(day),
            isDividerVisible = index < journeys.lastIndex
        )
    }

    private fun getLiteralDayByNumber(day: Int): String {
        return "day " + messageFormat.format(arrayOf(day))
    }
}
