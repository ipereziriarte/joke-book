package com.ipereziriarte.jokebook

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.NavHostController
import com.ipereziriarte.jokebook.ui.cover.Cover
import com.ipereziriarte.jokebook.ui.navigation.NavigationActions

import com.ipereziriarte.jokebook.ui.theme.JokeBookTheme
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test

class CoverTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val mockNavController = mockkClass(NavHostController::class)

    val navigationActions = NavigationActions(mockNavController)

    @Test
    fun cover_screen_should_contain_have_fun_button() {
        composeTestRule.setContent {
            JokeBookTheme {
                Cover(navigationActions)
            }
        }

        composeTestRule.onNodeWithTag("CoverMainBtn").assertExists()
    }
}
