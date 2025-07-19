package com.example.data.utils

import com.example.domain.models.ErrorGeneric
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response
import java.io.IOException
import java.io.InterruptedIOException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class HandleApiResponseTest {

    @Test
    fun `handleApiResponse success`() = runTest {
        val expectedResponse = Response.success(listOf("item1", "item2"))
        val apiCall: suspend () -> Response<List<String>> = { expectedResponse }
        val transform: suspend (List<String>) -> List<String> = { it }

        val result = handleApiResponse(apiCall, transform)

        assertTrue(result.isSuccess)
        assertEquals(expectedResponse.body(), result.getOrNull())
    }

    @Test
    fun `handleApiResponse error`() = runTest {
        val errorResponse = Response.error<List<String>>(404, "Generic Error".toResponseBody(null))
        val apiCall: suspend () -> Response<List<String>> = { errorResponse }
        val transform: suspend (List<String>) -> List<String> = { it }

        val result = handleApiResponse(apiCall, transform)

        val exception = assertFailsWith<ErrorGeneric> {
            throw result.exceptionOrNull() as ErrorGeneric
        }
        assertTrue(exception.description.isNotBlank())

        assertTrue(result.isFailure)
        val error = result.exceptionOrNull()
        assertTrue(error is Throwable)
    }

    @Test
    fun `handleApiResponse network error`() = runTest {
        val apiCall: suspend () -> Response<List<String>> = {
            throw InterruptedIOException("Connection timed out")
        }
        val transform: suspend (List<String>) -> List<String> = { it }

        val result = handleApiResponse(apiCall, transform)

        assertTrue(result.isFailure)
        val error = result.exceptionOrNull()
        assertTrue(error is Throwable)
    }

    @Test
    fun `handleApiResponse data error`() = runTest {
        val apiCall: suspend () -> Response<List<String>> =
            { Response.success(listOf("item1", "item2")) }
        val transform: suspend (List<String>) -> List<String> =
            { throw JsonDataException("Invalid JSON") }

        val result = handleApiResponse(apiCall, transform)

        assertTrue(result.isFailure)
        val error = result.exceptionOrNull()
        assertTrue(error is Throwable)
    }

    @Test
    fun `handleApiResponse unexpected exception`() = runTest {
        val apiCall: suspend () -> Response<List<String>> = { throw IOException("Network error") }
        val transform: suspend (List<String>) -> List<String> = { it }
        val result = handleApiResponse(apiCall, transform)

        assertTrue(result.isFailure)
        val exception = assertFailsWith<IOException> {
            throw result.exceptionOrNull() as IOException
        }
        assertTrue(exception.message.orEmpty().isNotBlank())
        val error = result.exceptionOrNull()
        assertTrue(error is Throwable)
    }
}