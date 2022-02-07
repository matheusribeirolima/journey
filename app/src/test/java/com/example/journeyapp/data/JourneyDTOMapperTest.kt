package com.example.journeyapp.data

import com.example.journeyapp.CleanTest
import com.google.gson.Gson
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.lang.reflect.Type

@DisplayName("Given a JourneyDTOMapper")
class JourneyDTOMapperTest : CleanTest {

    private val gson = mockk<Gson>()
    private val jsonString = "[]"
    private val slot = slot<Type>()
    private val journeyDTOMapper = JourneyDTOMapper(gson)

    @Nested
    @DisplayName("When convertJsonStringToJourneys is called")
    inner class ConvertJsonStringToJourneys {

        @Test
        @DisplayName("Then should use gson to convert")
        fun testConvertJsonStringToJourneys() {
            // Given
            every { gson.fromJson<List<JourneyDTO>>(jsonString, capture(slot)) } returns mockk()

            // When
            journeyDTOMapper.convertJsonStringToJourneys(jsonString)

            // Then
            verify(exactly = 1) { gson.fromJson<List<JourneyDTO>>(jsonString, slot.captured) }
        }
    }
}
