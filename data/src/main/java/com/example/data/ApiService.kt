package com.example.data

import com.example.data.response.DogResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(BuildConfig.API +"/" + BuildConfig.DOGS)
    suspend fun fetchDogs(): Response<List<DogResponse>?>
}
