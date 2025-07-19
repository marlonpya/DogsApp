package com.example.dogsapp.ui.state

import org.junit.Test
import kotlin.test.assertEquals

class UiStateTest {

    @Test
    fun `from should return INIT when the list is empty`() {
        val list = emptyList<Any>()

        val state = UiState.from(list)

        assertEquals(UiState.EMPTY, state)
    }

    @Test
    fun `from should return SUCCESS when the list is not empty`() {
        val list = listOf("item1", "item2")

        val state = UiState.from(list)

        assertEquals(UiState.SUCCESS, state)
    }

    @Test
    fun `from should return EMPTY when the list is null`() {
        val list: List<*>? = null

        val state = UiState.from(list.orEmpty())

        assertEquals(UiState.EMPTY, state)
    }

    @Test
    fun `from should return SUCCESS when the list contains null items`() {
        val list = listOf("item1", null, "item3")

        val state = UiState.from(list)

        assertEquals(UiState.SUCCESS, state)
    }
}