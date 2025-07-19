package com.example.dogsapp.ui.screens.dog_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dogsapp.utils.TestsUtil
import com.example.domain.fake.DogTestData
import com.example.domain.models.Dog
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun characterCard_displays_dog_information_correctly() {
        // Given
        val testDog = DogTestData.create()

        // When
        composeTestRule.setContent {
            CharacterCard(
                goToDetail = {},
                item = testDog
            )
        }

        // Then
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithText(testDog.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(testDog.description).assertIsDisplayed()
        composeTestRule.onNodeWithText("Almost ${testDog.age} years").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Dog image of Chief").assertIsDisplayed()
    }

    @Test
    fun characterCard_displays_single_year_correctly() {
        // Given
        val testDog = DogTestData.create().copy(age = 1)

        // When
        composeTestRule.setContent {
            CharacterCard(
                goToDetail = {},
                item = testDog
            )
        }

        // Then
        composeTestRule.onNodeWithText("Almost 1 year").assertIsDisplayed()
    }

    @Test
    fun characterCard_click_triggers_callback() {
        // Given
        var clickedDog: Dog? = null
        val testDog = DogTestData.create()

        // When
        composeTestRule.setContent {
            CharacterCard(
                goToDetail = { dog -> clickedDog = dog },
                item = testDog
            )
        }

        // Then
        composeTestRule.onNodeWithText(testDog.name)
            .assertIsDisplayed()
            .performClick()

        assert(clickedDog != null) { "Dog should have been clicked" }
        assert(clickedDog?.name == testDog.name) { "Clicked dog should match test data" }
    }
} 