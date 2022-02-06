package com.example.journeyapp.utils

import android.icu.text.MessageFormat
import com.example.journeyapp.data.JourneyDTO
import com.example.journeyapp.domain.Journey

fun List<JourneyDTO>.mapToViewJourney() = mapIndexed { index, journeyDTO ->
    val day = journeyDTO.day + 1
    Journey(
        title = journeyDTO.title,
        subtitle = journeyDTO.subtitle,
        day = day.toString(),
        literalDay = getLiteralDayByNumber(day),
        isDividerVisible = index < this.lastIndex
    )
}

private fun getLiteralDayByNumber(day: Int): String {
    return "day " + MessageFormat.format("{0,spellout,#}", day)
}
