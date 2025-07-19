package com.example.domain.fake

import com.example.domain.models.ErrorGeneric

object ErrorGenericTestData {
    const val code: Int = 404
    const val description: String = "Not Found"

    fun create() = ErrorGeneric(code, description)
}
