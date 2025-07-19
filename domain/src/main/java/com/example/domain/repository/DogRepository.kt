package com.example.domain.repository

import com.example.domain.models.Dog

interface DogRepository{
    suspend fun fetchDogs(): Result<List<Dog>>
}
