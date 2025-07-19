package com.example.domain.usecase

import com.example.domain.models.Dog
import com.example.domain.models.ErrorGeneric
import com.example.domain.repository.DogRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetDogsUseCaseTest {

    private val repository: DogRepository = mockk()

    private lateinit var useCase: GetDogsUseCase

    @Before
    fun setup() {
        useCase = GetDogsUseCase(
            repository, Dispatchers.Unconfined,
        )
    }

    @Test
    fun `invoke should return success result with data`() = runTest {
        val mockDogs = listOf<Dog>()
        coEvery { repository.fetchDogs() } returns Result.success(mockDogs)

        val result = useCase()

        assertTrue(result.isSuccess)
        assertEquals(mockDogs, result.getOrNull())
    }

    @Test
    fun `invoke should return failure result on error`() = runTest {
        val error = ErrorGeneric(404, "Not Found")
        coEvery { repository.fetchDogs() } returns Result.failure(error)

        val result = useCase()

        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }

    @Test
    fun `invoke should return failure result on repository error`() = runTest {
        val error = ErrorGeneric(404, "Not Found")
        coEvery {
            withContext(useCase.dispatcher) {
                repository.fetchDogs()
            }
        } returns Result.failure(error)

        val result = useCase()

        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }

    @Test
    fun `invoke should return failure result on context exception`() = runTest {

        coEvery { repository.fetchDogs() } coAnswers {
            withContext(useCase.dispatcher) {
                Result.failure(Exception("Error"))
            }
        }

        val result = useCase()

        assertTrue(result.isFailure)
    }
}