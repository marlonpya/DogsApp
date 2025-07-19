package com.example.dogsapp

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRecipesActivity() {
        composeTestRule.setContent {
            MainActivity()
        }
    }

}