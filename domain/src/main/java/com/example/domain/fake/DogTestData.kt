package com.example.domain.fake

import com.example.domain.models.Dog

object DogTestData {

    const val name = "Chief"
    const val description = "He is a leader of a pack of dogs"
    const val age = 8
    const val image =
        "https://static.wikia.nocookie.net/isle-of-dogs/images/1/1d/Chief-0.jpg/revision/latest/scale-to-width-down/666?cb=20180624184431"

    fun create(): Dog {
        return Dog(
            name = name,
            description = description,
            age = age,
            image = image
        )
    }
}