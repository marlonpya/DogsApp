package com.example.data

import com.example.data.response.DogResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

class ApiServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testFetchDogsSuccess() {

        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, DogResponse::class.java)
        val adapter = moshi.adapter<List<DogResponse>>(listType)
        val jsonFile = File("src/test/java/com/example/data/fake/response.json")
        val dogs: List<DogResponse> = adapter.fromJson(jsonFile.readText()) ?: emptyList()

        mockWebServer.enqueue(MockResponse().setBody(adapter.toJson(dogs)))

        runTest {
            val response = apiService.fetchDogs()
            assertTrue(response.isSuccessful)
            val result = response.body() ?: emptyList()
            assertNotNull(dogs)
            assertTrue(result.isNotEmpty())
        }
    }
}
