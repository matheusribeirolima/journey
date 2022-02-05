package com.example.journeyapp.data

import com.google.gson.annotations.SerializedName

data class JourneyDTO(
    @SerializedName("title") val title: String,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("day") val day: Int,
)
