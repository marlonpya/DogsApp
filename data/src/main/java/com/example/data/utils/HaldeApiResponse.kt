package com.example.data.utils

import com.example.domain.models.ErrorGeneric
import com.squareup.moshi.JsonDataException
import retrofit2.Response
import java.io.IOException
import java.io.InterruptedIOException

suspend inline fun <reified TResponse, reified TResult> handleApiResponse(
    crossinline apiCall: suspend () -> Response<TResponse>,
    crossinline transform: suspend (TResponse) -> TResult
): Result<TResult> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body() ?: throw JsonDataException("Response body is null")
            val result = transform(body)
            Result.success(result)
        } else {
            val fail = ErrorGeneric(response.code(), response.message())
            Result.failure(fail)
        }
    } catch (e: InterruptedIOException) {
        val fail = ErrorGeneric(408, e.message.orEmpty())
        Result.failure(fail)
    } catch (e: JsonDataException) {
        val fail = ErrorGeneric(0, e.message.orEmpty())
        Result.failure(fail)
    } catch (e: IOException) {
        Result.failure(e)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
