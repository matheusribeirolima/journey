package com.example.journeyapp

import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach

interface CleanTest {

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}
