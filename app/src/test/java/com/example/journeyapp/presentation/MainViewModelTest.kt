package com.example.journeyapp.presentation

import com.example.journeyapp.CoroutineCleanTest
import com.example.journeyapp.domain.Journey
import com.example.journeyapp.domain.JourneyUseCase
import com.example.journeyapp.domain.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.FileNotFoundException

@ExperimentalCoroutinesApi
@DisplayName("Given a MainViewModel")
class MainViewModelTest : CoroutineCleanTest() {

    private val journeyUseCase = mockk<JourneyUseCase>()
    private val mainViewModel = MainViewModel(testScope, journeyUseCase)

    @Nested
    @DisplayName("When journeys is collected")
    inner class Journeys {

        @Test
        @DisplayName("Then should first emit loading")
        fun testLoading() = testScope.runTest {
            // Given
            coEvery { journeyUseCase.loadData() } returns mockk()

            // When
            val result = mainViewModel.uiState.first()

            // Then
            assertEquals(result, Result.Loading)
        }

        @Test
        @DisplayName("Then should emit success after loadData")
        fun testSuccess() = testScope.runTest {
            // Given
            val journeys = mockk<List<Journey>>()
            coEvery { journeyUseCase.loadData() } returns journeys

            // When
            val result = mainViewModel.uiState.drop(1).first()

            // Then
            assertEquals(journeys, (result as Result.Success).result)
        }

        @Test
        @DisplayName("Then should emit error when receive exception")
        fun testError() = testScope.runTest {
            // Given
            coEvery { journeyUseCase.loadData() } throws FileNotFoundException()

            // When
            val result = mainViewModel.uiState.drop(1).first()

            // Then
            assert(result is Result.Error)
        }
    }
}
