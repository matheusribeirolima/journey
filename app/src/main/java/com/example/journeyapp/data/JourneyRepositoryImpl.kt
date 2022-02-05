package com.example.journeyapp.data

import com.example.journeyapp.utils.FileReader

class JourneyRepositoryImpl(
    private val fileReader: FileReader,
    private val journeyDTOMapper: JourneyDTOMapper
) : JourneyRepository {

    override fun loadData(fileName: String): List<JourneyDTO> {
        val jsonString = fileReader.readFile(fileName)
        return journeyDTOMapper.convertJsonStringToJourneys(jsonString)
    }
}
