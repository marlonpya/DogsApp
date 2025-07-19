package com.example.dogsapp.ui.screens.dog_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dogsapp.utils.TestsUtil
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TitleAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun titleAppBar_displays_title_correctly() {
        // When
        composeTestRule.setContent {
            TitleAppBar()
        }

        // Then
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithText("Dogs We Love").assertIsDisplayed()
    }

    @Test
    fun titleAppBar_displays_back_button() {
        // When
        composeTestRule.setContent {
            TitleAppBar()
        }

        // Then
        composeTestRule.onNodeWithContentDescription("Volver").assertIsDisplayed()
    }

    @Test
    fun titleAppBar_components_are_visible() {
        // When
        composeTestRule.setContent {
            TitleAppBar()
        }

        // Then
        composeTestRule.onNodeWithText("Dogs We Love").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Volver").assertIsDisplayed()
    }
} 