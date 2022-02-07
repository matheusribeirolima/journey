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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JourneyModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideMessageFormat(): MessageFormat = MessageFormat("{0,spellout,#}")

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideCoroutineScope(): CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    @Provides
    @Singleton
    fun provideJourneyRepository(
        fileReader: FileReader,
        journeyDTOMapper: JourneyDTOMapper,
        coroutineDispatcher: CoroutineDispatcher,
    ): JourneyRepository = JourneyRepositoryImpl(fileReader, journeyDTOMapper, coroutineDispatcher)
}
