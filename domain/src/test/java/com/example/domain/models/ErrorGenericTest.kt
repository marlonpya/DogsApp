package com.example.domain.models

import com.example.domain.fake.ErrorGenericTestData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class ErrorGenericTest {

    @Test
    fun testErrorGenericInitialization() {
        val testData = ErrorGenericTestData.create()
        val error = ErrorGeneric(testData.code, testData.description)

        assertEquals(testData.code, error.code)
        assertEquals(testData.description, error.description)
    }

    @Test
    fun testErrorEquality() {
        val error1 = ErrorGenericTestData.create()
        val error2 = ErrorGenericTestData.create()

        assertEquals(error1.code, error2.code)
        assertEquals(error1.description, error2.description)
    }

    @Test
    fun testErrorInheritance() {
        val testData = ErrorGenericTestData.create()
        val error = ErrorGeneric(testData.code, testData.description) as? Exception

        assertNotNull(error)
    }

}