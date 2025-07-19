package com.example.domain.models

import com.example.domain.fake.DogTestData
import org.junit.Assert.assertEquals
import org.junit.Test

class DogTest {

    @Test
    fun testDogInitialization() {
        val testData = DogTestData.create()
        val recipe = Dog(
            name = testData.name,
            description = testData.description,
            age = testData.age,
            image = testData.image
        )

        assertEquals(testData.name, recipe.name)
        assertEquals(testData.description, recipe.description)
        assertEquals(testData.age, recipe.age)
        assertEquals(testData.image, recipe.image)
    }

    @Test
    fun testDogEquality() {
        val recipe1 = DogTestData.create()
        val recipe2 = DogTestData.create()

        assertEquals(recipe1.name, recipe2.name)
        assertEquals(recipe1.description, recipe2.description)
        assertEquals(recipe1.age, recipe2.age)
        assertEquals(recipe1.image, recipe2.image)
    }
}