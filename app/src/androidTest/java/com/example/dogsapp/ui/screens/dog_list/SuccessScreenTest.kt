package com.example.dogsapp.ui.screens.dog_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.example.dogsapp.utils.TestsUtil
import com.example.domain.fake.DogTestData
import org.junit.Rule
import org.junit.Test

class SuccessScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSuccessStateVisible() {
        composeTestRule.setContent {
            SuccessScreen(
                isVisible = true,
                dogs = listOf(DogTestData.create()),
                goToDetail = {}
            )
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.homeContent).assertIsDisplayed()
    }

    @Test
    fun testSuccessStateInvisible() {
        composeTestRule.setContent {
            SuccessScreen(
                isVisible = false,
                dogs = listOf(DogTestData.create()),
                goToDetail = {}
            )
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.homeContent).assertDoesNotExist()
    }

    @Test
    fun testSuccessToMaps() {
        composeTestRule.setContent {
            SuccessScreen(
                isVisible = true,
                dogs = listOf(DogTestData.create()),
                goToDetail = {}
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.homeContent)
            .performClick()
            .assertExists()
    }
}