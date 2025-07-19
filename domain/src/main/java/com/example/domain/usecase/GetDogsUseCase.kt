package com.example.domain.usecase

import com.example.domain.models.Dog
import com.example.domain.repository.DogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class GetDogsUseCase  @Inject constructor(
    private val repository: DogRepository,
    @Named("DispatcherIO") val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Result<List<Dog>> {
        return withContext(dispatcher) { repository.fetchDogs() }
    }
}
