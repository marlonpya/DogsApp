package com.example.dogsapp.ui.state

import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MutableUiStateHolderTest {
    private lateinit var uiStateHolder: UiStateHolder

    @Before
    fun setUp() {
        uiStateHolder = MutableUiStateHolder()
    }

    @Test
    fun `isLoadingVisible should be true when currentState is LOADING`() {
        uiStateHolder.currentState = UiState.LOADING

        val isLoadingVisible = uiStateHolder.isLoadingVisible

        assertTrue(isLoadingVisible)
    }

    @Test
    fun `isErrorVisible should be true when currentState is ERROR`() {
        uiStateHolder.currentState = UiState.ERROR

        val isErrorVisible = uiStateHolder.isErrorVisible

        assertTrue(isErrorVisible)
    }

    @Test
    fun `isSuccessVisible should be true when currentState is SUCCESS`() {
        uiStateHolder.currentState = UiState.SUCCESS

        val isSuccessVisible = uiStateHolder.isSuccessVisible

        assertTrue(isSuccessVisible)
    }

    @Test
    fun `isEmptyVisible should be true when currentState is EMPTY`() {
        uiStateHolder.currentState = UiState.EMPTY

        val isEmptyVisible = uiStateHolder.isEmptyVisible

        assertTrue(isEmptyVisible)
    }

    @Test
    fun `isLoadingVisible should be false when currentState is not LOADING`() {
        uiStateHolder.currentState = UiState.SUCCESS

        val isLoadingVisible = uiStateHolder.isLoadingVisible

        assertFalse(isLoadingVisible)
    }

    @Test
    fun `isErrorVisible should be false when currentState is not ERROR`() {
        uiStateHolder.currentState = UiState.SUCCESS

        val isErrorVisible = uiStateHolder.isErrorVisible

        assertFalse(isErrorVisible)
    }

    @Test
    fun `isSuccessVisible should be false when currentState is not SUCCESS`() {
        uiStateHolder.currentState = UiState.LOADING

        val isSuccessVisible = uiStateHolder.isSuccessVisible

        assertFalse(isSuccessVisible)
    }

    @Test
    fun `isEmptyVisible should be false when currentState is not EMPTY`() {
        uiStateHolder.currentState = UiState.SUCCESS

        val isEmptyVisible = uiStateHolder.isEmptyVisible

        assertFalse(isEmptyVisible)
    }
}