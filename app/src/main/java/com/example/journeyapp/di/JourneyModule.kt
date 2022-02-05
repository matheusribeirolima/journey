package com.example.journeyapp.di

import android.icu.text.MessageFormat
import com.example.journeyapp.data.JourneyDTOMapper
import com.example.journeyapp.data.JourneyRepository
import com.example.journeyapp.data.JourneyRepositoryImpl
import com.example.journeyapp.utils.FileReader
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JourneyModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideLocale(): Locale = Locale.getDefault()

    @Provides
    @Singleton
    fun provideMessageFormat(locale: Locale): MessageFormat = MessageFormat(
        "{0,spellout}",
        locale,
    )

    @Provides
    @Singleton
    fun provideJourneyRepository(
        fileReader: FileReader,
        journeyDTOMapper: JourneyDTOMapper,
    ): JourneyRepository = JourneyRepositoryImpl(fileReader, journeyDTOMapper)
}
