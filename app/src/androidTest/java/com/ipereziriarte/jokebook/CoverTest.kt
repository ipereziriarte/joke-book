package com.ipereziriarte.jokebook

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.ipereziriarte.jokebook.ui.cover.Cover
import com.ipereziriarte.jokebook.ui.theme.JokeBookTheme
import org.junit.Rule
import org.junit.Test

class CoverTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `cover screen should contain have fun button`() {
        composeTestRule.setContent {
            JokeBookTheme {
                Cover {
                }
            }
        }

        composeTestRule.onNodeWithTag("CoverMainBtn").assertExists()
    }
}
