package com.example.journeyapp.domain

import android.icu.text.MessageFormat
import com.example.journeyapp.CleanTest
import com.example.journeyapp.data.JourneyDTO
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("Given a JourneyMapper")
class JourneyMapperTest : CleanTest() {

    private val journeyDTO = JourneyDTO(
        title = "title",
        subtitle = "subtitle",
        day = 0,
    )
    private val journeyDTOs = listOf(journeyDTO)
    private val messageFormat = mockk<MessageFormat>()

    private val journeyMapper = JourneyMapper(messageFormat)

    @Nested
    @DisplayName("When mapToViewJourney is called")
    inner class MapToViewJourney {

        @Test
        @DisplayName("Then should return a respective Journey")
        fun testMapToViewJourneyReturn() {
            // Given
            val literalDay = "one"
            every { messageFormat.format(any()) } returns literalDay

            val expect = Journey(
                title = "title",
                subtitle = "subtitle",
                day = "1",
                literalDay = "day $literalDay",
                isDividerVisible = false,
            )

            // When
            val result = journeyMapper.mapToViewJourney(journeyDTOs).first()

            // Then
            assertAll(
                heading = "Validate each Journey's field",
                { assertEquals(expect.title, result.title) },
                { assertEquals(expect.subtitle, result.subtitle) },
                { assertEquals(expect.day, result.day) },
                { assertEquals(expect.literalDay, result.literalDay) },
                { assertEquals(expect.isDividerVisible, result.isDividerVisible) },
            )
        }
    }
}
