package com.example.dogsapp.ui.screens.dog_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dogsapp.ui.state.UiState
import com.example.dogsapp.utils.TestsUtil
import com.example.domain.fake.DogTestData
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DogsListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun dogsListScreen_loading_state_displays_correctly() {
        // Given
        val testData = DogsListData(
            currentState = UiState.LOADING,
            dogs = emptyList()
        )

        // When
        composeTestRule.setContent {
            DogsListScreen(
                state = { testData },
                goToDetail = {},
                retry = {}
            )
        }

        // Then
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.homeScreen).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Loading").assertIsDisplayed()
    }

    @Test
    fun dogsListScreen_error_state_displays_correctly() {
        // Given
        val testData = DogsListData(
            currentState = UiState.ERROR,
            dogs = emptyList()
        )

        // When
        composeTestRule.setContent {
            DogsListScreen(
                state = { testData },
                goToDetail = {},
                retry = {}
            )
        }

        // Then
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.homeScreen).assertIsDisplayed()
        composeTestRule.onNodeWithText("Ocurrió un error, toque aquí para reintentar").assertIsDisplayed()
    }

    @Test
    fun dogsListScreen_empty_state_displays_correctly() {
        // Given
        val testData = DogsListData(
            currentState = UiState.EMPTY,
            dogs = emptyList()
        )

        // When
        composeTestRule.setContent {
            DogsListScreen(
                state = { testData },
                goToDetail = {},
                retry = {}
            )
        }

        // Then
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.homeScreen).assertIsDisplayed()
        composeTestRule.onNodeWithText("No data available").assertIsDisplayed()
    }

    @Test
    fun dogsListScreen_success_state_displays_correctly() {
        // Given
        val testDogs = listOf(DogTestData.create())
        val testData = DogsListData(
            currentState = UiState.SUCCESS,
            dogs = testDogs
        )

        // When
        composeTestRule.setContent {
            DogsListScreen(
                state = { testData },
                goToDetail = {},
                retry = {}
            )
        }

        // Then
        composeTestRule.onRoot(useUnmergedTree = true).printToLog(TestsUtil.tagHomeScreen)
        composeTestRule.onNodeWithContentDescription(TestsUtil.homeScreen).assertIsDisplayed()
        composeTestRule.onNodeWithText(DogTestData.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(DogTestData.description).assertIsDisplayed()
    }

    @Test
    fun dogsListScreen_title_appbar_displays_correctly() {
        // Given
        val testData = DogsListData(
            currentState = UiState.SUCCESS,
            dogs = listOf(DogTestData.create())
        )

        // When
        composeTestRule.setContent {
            DogsListScreen(
                state = { testData },
                goToDetail = {},
                retry = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Dogs We Love").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Volver").assertIsDisplayed()
    }

    @Test
    fun dogsListScreen_error_retry_click_works() {
        // Given
        var retryClicked = false
        val testData = DogsListData(
            currentState = UiState.ERROR,
            dogs = emptyList()
        )

        // When
        composeTestRule.setContent {
            DogsListScreen(
                state = { testData },
                goToDetail = {},
                retry = { retryClicked = true }
            )
        }

        // Then
        composeTestRule.onNodeWithText("Ocurrió un error, toque aquí para reintentar")
            .assertIsDisplayed()
            .performClick()

        assert(retryClicked) { "Retry callback should have been called" }
    }

    @Test
    fun dogsListScreen_dog_item_click_works() {
        // Given
        var clickedDog: com.example.domain.models.Dog? = null
        val testDogs = listOf(DogTestData.create())
        val testData = DogsListData(
            currentState = UiState.SUCCESS,
            dogs = testDogs
        )

        // When
        composeTestRule.setContent {
            DogsListScreen(
                state = { testData },
                goToDetail = { dog -> clickedDog = dog },
                retry = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText(DogTestData.name)
            .assertIsDisplayed()
            .performClick()

        assert(clickedDog != null) { "Dog should have been clicked" }
        assert(clickedDog?.name == DogTestData.name) { "Clicked dog should match test data" }
    }

    @Test
    fun dogsListScreen_multiple_dogs_display_correctly() {
        // Given
        val testDogs = listOf(
            DogTestData.create(),
            DogTestData.create().copy(description = "He is a leader of a pack of dogs"),
            DogTestData.create().copy(name = "Boss", description = "Little is known about Boss' origins other than he was the mascot for the Megasaki Dragons")
        )
        val testData = DogsListData(
            currentState = UiState.SUCCESS,
            dogs = testDogs
        )

        // When
        composeTestRule.setContent {
            DogsListScreen(
                state = { testData },
                goToDetail = {},
                retry = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Chief").assertIsDisplayed()
        composeTestRule.onNodeWithText("Boss").assertIsDisplayed()
    }
} 