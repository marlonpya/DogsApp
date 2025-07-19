package com.example.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogResponse(
    val dogName: String?,
    val description: String?,
    val age: Int?,
    val image: String?
)