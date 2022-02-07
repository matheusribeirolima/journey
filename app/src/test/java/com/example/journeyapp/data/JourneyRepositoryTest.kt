package com.example.journeyapp.data

import com.example.journeyapp.CoroutineCleanTest
import com.example.journeyapp.utils.FileReader
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
@DisplayName("Given a JourneyRepository")
class JourneyRepositoryTest : CoroutineCleanTest() {

    private val fileReader = mockk<FileReader>()
    private val journeyDTOMapper = mockk<JourneyDTOMapper>()
    private val fileName = "data.json"
    private val fileData = "data"
    private val journeyRepository = JourneyRepositoryImpl(
        fileReader,
        journeyDTOMapper,
        dispatcher,
    )

    @Nested
    @DisplayName("When loadData is called")
    inner class LoadData {

        @Test
        @DisplayName("Then should call readFile and convertJsonStringToJourneys")
        fun testLoadData() = testScope.runTest {
            // Given
            val fileNameSlot = slot<String>()
            val fileDataSlot = slot<String>()
            every { fileReader.readFile(capture(fileNameSlot)) } returns fileData
            every { journeyDTOMapper.convertJsonStringToJourneys(capture(fileDataSlot)) } returns mockk()

            // When
            journeyRepository.loadData(fileName)

            // Then
            verifyOrder {
                fileReader.readFile(fileNameSlot.captured)
                journeyDTOMapper.convertJsonStringToJourneys(fileDataSlot.captured)
            }
        }
    }
}
