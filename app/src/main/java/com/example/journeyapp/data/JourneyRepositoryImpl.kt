package com.example.journeyapp.data

import com.example.journeyapp.utils.FileReader
import kotlinx.coroutines.delay

private const val FAKE_NETWORK_CALL_DELAY = 2000L

class JourneyRepositoryImpl(
    private val fileReader: FileReader,
    private val journeyDTOMapper: JourneyDTOMapper
) : JourneyRepository {

    override suspend fun loadData(fileName: String): List<JourneyDTO> {
        delay(FAKE_NETWORK_CALL_DELAY)
        val jsonString = fileReader.readFile(fileName)
        return journeyDTOMapper.convertJsonStringToJourneys(jsonString)
    }
}
