package com.example.data.response

import com.example.data.fake.DogResponseTestData
import org.junit.Assert.assertEquals
import org.junit.Test

class DogResponseTest {

    @Test
    fun testDogResponseInitialization() {
        val locationEntity = DogResponseTestData.createDogResponse()

        assertEquals(DogResponseTestData.name, locationEntity.dogName)
        assertEquals(DogResponseTestData.description, locationEntity.description)
        assertEquals(DogResponseTestData.age, locationEntity.age)
        assertEquals(DogResponseTestData.image, locationEntity.image)
    }

    @Test
    fun testDogResponseEquality() {
        val locationEntity1 = DogResponseTestData.createDogResponse()
        val locationEntity2 = DogResponseTestData.createDogResponse()

        assertEquals(locationEntity1.dogName, locationEntity2.dogName)
        assertEquals(locationEntity1.description, locationEntity2.description)
        assertEquals(locationEntity1.age, locationEntity2.age)
        assertEquals(locationEntity1.image, locationEntity2.image)
    }
}