package com.example.data.repositoryimpl

import com.example.data.response.DogResponse

object DogMock {
    val dataTake = listOf(
        DogResponse(
            dogName = "Spots",
            description = "Is the brother of Chief and are also a former guard dog for Mayor Kobayashi's ward",
            age = 3,
            image = "https://static.wikia.nocookie.net/isle-of-dogs/images/6/6b/Spots.jpg/revision/latest/scale-to-width-down/666?cb=20180624191101"
        ),
        DogResponse(
            dogName = null,
            description = null,
            age = null,
            image = null
        ),
    )
}