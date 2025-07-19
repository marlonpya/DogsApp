package com.example.data.di

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class NetworkModuleTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("DispatcherIO")
    lateinit var dispatcherIO: CoroutineDispatcher

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testDispatcherIOInjection() {
        assertNotNull(dispatcherIO)
    }

    @Test
    fun testOkHttpClientInjection() {
        assertNotNull(okHttpClient)
    }
}
