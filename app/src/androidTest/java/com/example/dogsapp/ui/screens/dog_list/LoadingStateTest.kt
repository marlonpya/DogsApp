package com.example.dogsapp.ui.screens.dog_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dogsapp.utils.TestsUtil
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoadingStateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingState_visible_displays_progress_indicator() {
        // When
        composeTestRule.setContent {
            LoadingState(isVisible = true)
        }

        // Then
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription("Loading").assertIsDisplayed()
    }

    @Test
    fun loadingState_invisible_does_not_display() {
        // When
        composeTestRule.setContent {
            LoadingState(isVisible = false)
        }

        // Then
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription("Loading").assertDoesNotExist()
    }
} 