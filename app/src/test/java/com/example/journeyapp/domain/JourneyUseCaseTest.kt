package com.example.journeyapp.domain

import com.example.journeyapp.CoroutineCleanTest
import com.example.journeyapp.data.JourneyRepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
@DisplayName("Given a JourneyUseCase")
class JourneyUseCaseTest : CoroutineCleanTest() {

    private val journeyRepository = mockk<JourneyRepository>()
    private val journeyMapper = mockk<JourneyMapper>()
    private val fileName = "data.json"
    private val journeyUseCase = JourneyUseCase(journeyRepository, journeyMapper)

    @Nested
    @DisplayName("When loadData is called")
    inner class LoadData {

        @Test
        @DisplayName("Then should call loadData with 'data.json' and mapToViewJourney")
        fun testLoadData() = testScope.runTest {
            // Given
            val fileNameSlot = slot<String>()
            coEvery { journeyRepository.loadData(capture(fileNameSlot)) } returns mockk()
            every { journeyMapper.mapToViewJourney(any()) } returns mockk()

            // When
            journeyUseCase.loadData()

            // Then
            assertEquals(fileName, fileNameSlot.captured)
            coVerifySequence {
                journeyRepository.loadData(fileNameSlot.captured)
                journeyMapper.mapToViewJourney(any())
            }
        }
    }
}
