package com.example.data.mapper

import com.example.data.fake.DogResponseTestData
import org.junit.Assert.assertEquals
import org.junit.Test

class DogMapperTest {
    @Test
    fun testDogResponseInitialization() {
        val entity = DogResponseTestData.createDogResponse()

        with(DogResponseTestData) {
            assertEquals(name, entity.dogName)
            assertEquals(description, entity.description)
            assertEquals(age, entity.age)
            assertEquals(image, entity.image)
        }
    }

    @Test
    fun testRecipeEntityEquality() {
        val recipeEntity1 = DogResponseTestData.createDogResponse()
        val recipeEntity2 = DogResponseTestData.createDogResponse()

        assertEquals(recipeEntity1.dogName, recipeEntity2.dogName)
    }
}