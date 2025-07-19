package com.example.dogsapp.ui.screens.dog_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dogsapp.utils.TestsUtil
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmptyStateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun emptyState_visible_displays_empty_message() {
        // When
        composeTestRule.setContent {
            EmptyState(isVisible = true)
        }

        // Then
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithText("No data available").assertIsDisplayed()
    }

    @Test
    fun emptyState_invisible_does_not_display() {
        // When
        composeTestRule.setContent {
            EmptyState(isVisible = false)
        }

        // Then
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithText("No data available").assertDoesNotExist()
    }
} 