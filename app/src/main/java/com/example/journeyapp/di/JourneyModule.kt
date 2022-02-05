package com.example.journeyapp.di

import com.example.journeyapp.data.JourneyDTOMapper
import com.example.journeyapp.data.JourneyRepositoryImpl
import com.example.journeyapp.utils.FileReader
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JourneyModule {

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideJourneyRepository(fileReader: FileReader, journeyDTOMapper: JourneyDTOMapper) =
        JourneyRepositoryImpl(fileReader, journeyDTOMapper)
}
