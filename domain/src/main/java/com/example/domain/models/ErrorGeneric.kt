package com.example.domain.models

class ErrorGeneric(
    val code: Int,
    val description: String,
) : Exception(description)