package com.example.dogsapp.ui.screens.dog_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dogsapp.utils.TestsUtil
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ErrorScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorScreen_visible_displays_error_message() {
        // When
        composeTestRule.setContent {
            ErrorScreen(isVisible = true, retry = {})
        }

        // Then
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithText("Ocurrió un error, toque aquí para reintentar").assertIsDisplayed()
    }

    @Test
    fun errorScreen_invisible_does_not_display() {
        // When
        composeTestRule.setContent {
            ErrorScreen(isVisible = false, retry = {})
        }

        // Then
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithText("Ocurrió un error, toque aquí para reintentar").assertDoesNotExist()
    }

    @Test
    fun errorScreen_retry_click_triggers_callback() {
        // Given
        var retryClicked = false

        // When
        composeTestRule.setContent {
            ErrorScreen(isVisible = true, retry = { retryClicked = true })
        }

        // Then
        composeTestRule.onNodeWithText("Ocurrió un error, toque aquí para reintentar")
            .assertIsDisplayed()
            .performClick()

        assert(retryClicked) { "Retry callback should have been triggered" }
    }
} 