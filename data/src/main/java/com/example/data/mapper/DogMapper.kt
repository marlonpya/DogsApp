package com.example.data.mapper

import com.example.data.response.DogResponse
import com.example.domain.models.Dog

fun DogResponse.toModel() = Dog(
    name = dogName ?: "",
    description = description ?: "",
    age = age ?: 0,
    image = image ?: ""
)