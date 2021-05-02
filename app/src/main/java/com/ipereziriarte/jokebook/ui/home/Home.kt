package com.ipereziriarte.jokebook.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.ipereziriarte.jokebook.ui.cover.Cover
import com.ipereziriarte.jokebook.ui.jokelist.JokeList
import com.ipereziriarte.jokebook.ui.navigation.MainDestinations.CHILDSCREEN
import com.ipereziriarte.jokebook.ui.navigation.MainDestinations.CHILD_TITLE_KEY
import com.ipereziriarte.jokebook.ui.navigation.MainDestinations.COVERSCREEN
import com.ipereziriarte.jokebook.ui.navigation.MainDestinations.LISTSCREEN
import com.ipereziriarte.jokebook.ui.navigation.NavigationActions
import com.ipereziriarte.jokebook.ui.punchline.Punchline
import com.ipereziriarte.jokebook.ui.theme.JokeBookTheme

@Composable
internal fun Home() {
    val navController = rememberNavController()
    val actions = remember(navController) { NavigationActions(navController) }

    NavHost(navController = navController, startDestination = COVERSCREEN) {
        composable(COVERSCREEN) {
            Cover(actions)
        }
        composable(LISTSCREEN) {
            JokeList(actions)
        }
        composable(
            "$CHILDSCREEN/{$CHILD_TITLE_KEY}",
            arguments = listOf(navArgument(CHILD_TITLE_KEY) { type = NavType.StringType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            Punchline(actions = actions, punchline = arguments.getString(CHILD_TITLE_KEY, ""))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JokeBookTheme {
        Home()
    }
}
