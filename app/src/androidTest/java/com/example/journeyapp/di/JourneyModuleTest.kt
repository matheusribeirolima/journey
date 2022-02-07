package com.example.journeyapp.di

import android.icu.text.MessageFormat
import com.example.journeyapp.data.JourneyRepository
import com.google.gson.Gson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class JourneyModuleTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var messageFormat: MessageFormat

    @Inject
    lateinit var coroutineDispatcher: CoroutineDispatcher

    @Inject
    lateinit var coroutineScope: CoroutineScope

    @Inject
    lateinit var journeyRepository: JourneyRepository

    @Test
    fun testJourneyModuleProvidesInjection() {
        hiltRule.inject()
    }
}
