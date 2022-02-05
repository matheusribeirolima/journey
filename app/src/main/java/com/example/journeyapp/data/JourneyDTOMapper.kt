package com.example.journeyapp.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class JourneyDTOMapper @Inject constructor(private val gson: Gson) {

    fun convertJsonStringToJourneys(jsonString: String): List<JourneyDTO> {
        val journeys = object : TypeToken<List<JourneyDTO>>() {}.type
        return gson.fromJson(jsonString, journeys)
    }
}
