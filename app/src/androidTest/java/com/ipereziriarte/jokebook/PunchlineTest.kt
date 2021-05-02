package com.ipereziriarte.jokebook

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.navigation.NavHostController
import com.ipereziriarte.jokebook.ui.navigation.NavigationActions
import com.ipereziriarte.jokebook.ui.punchline.Punchline
import com.ipereziriarte.jokebook.ui.theme.JokeBookTheme
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class PunchlineTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val mockNavController = mockkClass(NavHostController::class)

    val navigationActions = NavigationActions(mockNavController)

    @Test
    fun punchline_screen_should_show_punchline_text_when_provided() {
        val punchline = "Try it out on Internet Explorer"
        composeTestRule.setContent {
            JokeBookTheme {
                Punchline(actions = navigationActions, punchline)
            }
        }

        composeTestRule.onNodeWithText(punchline).assertExists()
    }

    @Test
    fun punchline_screen_should_navigate_back_when_card_is_clicked() {
        val punchline = "Try it out on Internet Explorer"
        val actions = mockkClass(NavigationActions::class)
        every { actions.navigateBack() } returns Unit

        composeTestRule.setContent {
            JokeBookTheme {
                Punchline(actions, punchline)
            }
        }

        composeTestRule.onRoot().printToLog("TAG")
        composeTestRule.onRoot().onChild().performClick()

        verify { actions.navigateBack() }
    }
}
