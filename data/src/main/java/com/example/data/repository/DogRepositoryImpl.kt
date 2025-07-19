package com.example.data.repository

import com.example.data.ApiService
import com.example.data.mapper.toModel
import com.example.domain.models.Dog
import com.example.domain.models.ErrorGeneric
import com.example.domain.repository.DogRepository
import com.squareup.moshi.JsonDataException
import java.io.IOException
import java.io.InterruptedIOException
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : DogRepository {

    override suspend fun fetchDogs(): Result<List<Dog>> {
        return try {
            val response = apiService.fetchDogs()
            if (response.isSuccessful) {
                val body = response.body() ?: throw JsonDataException("Response body is null")
                val result = body.map { it.toModel() }
                Result.success(result)
            } else {
                val fail = ErrorGeneric(response.code(), response.message())
                Result.failure(fail)
            }
        } catch (e: Exception) {
            println(e.message)
            val error = when (e) {
                is InterruptedIOException -> ErrorGeneric(408, e.message.orEmpty())
                is JsonDataException -> ErrorGeneric(0, e.message.orEmpty())
                is IOException -> e
                else -> e
            }
            Result.failure(error)
        }
    }
}
