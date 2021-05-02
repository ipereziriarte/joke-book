package com.ipereziriarte.jokebook

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.NavHostController
import com.ipereziriarte.data.repository.Joke
import com.ipereziriarte.jokebook.ui.jokelist.ShowBody
import com.ipereziriarte.jokebook.ui.navigation.NavigationActions
import com.ipereziriarte.jokebook.ui.theme.JokeBookTheme
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test

class JokeListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val mockNavController = mockkClass(NavHostController::class)

    val navigationActions = NavigationActions(mockNavController)

    @Test
    fun jokeList_should_contain_correct_number_of_rows() {
        val jokes = listOf(
            Joke("What do you call a pig with three eyes", "Piig"),
            Joke(
                "what is the difference between ignorance and apathy?",
                "I don't know and I don't care"
            ),
            Joke(
                "A weasel walks into a bar. The bartender says, \"Wow, I've never served a weasel before. What can I get for you?\"",
                "Pop,goes the weasel."
            )
        )

        composeTestRule.setContent {
            JokeBookTheme {
                ShowBody(jokes, navigationActions)
            }
        }

        composeTestRule.onNodeWithTag("JokesColumn").onChildren().assertCountEquals(jokes.size)
    }
}
