package com.example.journeyapp.data

import com.example.journeyapp.utils.FileReader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val FAKE_NETWORK_CALL_DELAY = 2000L

class JourneyRepositoryImpl(
    private val fileReader: FileReader,
    private val journeyDTOMapper: JourneyDTOMapper,
    private val coroutineDispatcher: CoroutineDispatcher,
) : JourneyRepository {

    override suspend fun loadData(fileName: String): List<JourneyDTO> =
        withContext(coroutineDispatcher) {
            delay(FAKE_NETWORK_CALL_DELAY)
            val jsonString = fileReader.readFile(fileName)
            journeyDTOMapper.convertJsonStringToJourneys(jsonString)
        }
}
