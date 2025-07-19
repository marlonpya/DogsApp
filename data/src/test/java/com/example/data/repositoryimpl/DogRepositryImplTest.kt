package com.example.data.repositoryimpl

import com.example.data.ApiService
import com.example.data.fake.DogResponseTestData
import com.example.data.mapper.toModel
import com.example.data.repository.DogRepositoryImpl
import com.example.data.response.DogResponse
import com.example.data.utils.handleApiResponse
import com.example.domain.models.Dog
import com.example.domain.models.ErrorGeneric
import com.squareup.moshi.JsonDataException
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.InterruptedIOException

class DogRepositryImplTest {

    private lateinit var repository: DogRepositoryImpl
    private val apiService = mockk<ApiService>()

    @Before
    fun setUp() {
        repository = DogRepositoryImpl(apiService)
    }

    @Test
    fun `fetchRecipes success`() = runTest {
        val response = Response.success(listOf(DogResponseTestData.createDogResponse()))
        coEvery { apiService.fetchDogs() } returns response

        val result = repository.fetchDogs()

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
    }

    @Test
    fun `fetchRecipes error`() = runTest {
        val response = Response.error<List<DogResponse>>(404, "".toResponseBody(null))
        coEvery { apiService.fetchDogs() } returns response

        val result = repository.fetchDogs()

        assertTrue(result.isFailure)
        assertEquals(404, (result.exceptionOrNull() as ErrorGeneric).code)
    }

    @Test
    fun `handleApiResponse success`() = runTest {
        coEvery { apiService.fetchDogs() } coAnswers {
            val response = Response.success(listOf(DogResponseTestData.createDogResponse()))
            response
        }

        val result = handleApiResponse(
            apiCall = { apiService.fetchDogs() },
            transform = { entities -> entities?.map { it.toModel() } }
        )

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
    }

    @Test
    fun `handleApiResponse error`() = runTest {
        coEvery { apiService.fetchDogs() } coAnswers {
            val response = Response.error<List<DogResponse>>(404, "".toResponseBody(null))
            response
        }

        val result = handleApiResponse(
            apiCall = { apiService.fetchDogs() },
            transform = { entities -> entities?.map { it.toModel() } }
        )

        assertTrue(result.isFailure)
        assertEquals(404, (result.exceptionOrNull() as ErrorGeneric).code)
    }

    @Test
    fun `handleApiResponse network error`() = runTest {
        val responseSlot = slot<suspend () -> Response<List<DogResponse>>>()
        coEvery { apiService.fetchDogs() } coAnswers {
            responseSlot.captured.invoke()
            throw InterruptedIOException("Connection timed out")
        }

        val result = handleApiResponse(
            apiCall = { apiService.fetchDogs() },
            transform = { entities -> entities?.map { it.toModel() } }
        )

        assertTrue(result.isFailure)
        assert(result.exceptionOrNull() is Throwable)
    }

    @Test
    fun `handleApiResponse data error`() = runTest {
        val responseSlot = slot<suspend () -> Response<List<DogResponse>>>()
        coEvery { apiService.fetchDogs() } coAnswers {
            responseSlot.captured()
            throw JsonDataException(DogResponseTestData.recipeJsonError)
        }

        val result = handleApiResponse(
            apiCall = { apiService.fetchDogs() },
            transform = { entities -> entities?.map { it.toModel() } }
        )

        assertTrue(result.isFailure)
        assert(result.exceptionOrNull() is Throwable)
    }

    @Test
    fun isSuccess() = runTest {
        val repository = DogRepositoryImpl(FakeSuccessApiService())
        val result: Result<List<Dog>> = repository.fetchDogs()
        assert(result.isSuccess)
    }

    @Test
    fun isNoEmpty() = runTest {
        val dataFake = DogMock.dataTake
        val dataConverted = dataFake.map { it.toModel() }
        assert(dataConverted.isNotEmpty())
    }

    @Test
    fun isError() = runTest {
        val repository = DogRepositoryImpl(FakeErrorApiService())
        val result: Result<List<Dog>> = repository.fetchDogs()
        assert(result.isFailure)
    }
}

class FakeSuccessApiService : ApiService {
    override suspend fun fetchDogs(): Response<List<DogResponse>?> {
        return Response.success(emptyList())
    }
}

class FakeErrorApiService : ApiService {
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override suspend fun fetchDogs(): Response<List<DogResponse>?> {
        return Response.error(401, null)
    }
}
