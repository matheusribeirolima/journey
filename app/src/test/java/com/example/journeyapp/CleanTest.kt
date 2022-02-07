package com.example.journeyapp

import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach

abstract class CleanTest {

    @AfterEach
    open fun tearDown() {
        unmockkAll()
    }
}
