package com.ipereziriarte.jokebook.ui.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.ipereziriarte.jokebook.ui.navigation.MainDestinations.CHILDSCREEN
import com.ipereziriarte.jokebook.ui.navigation.MainDestinations.COVERSCREEN
import com.ipereziriarte.jokebook.ui.navigation.MainDestinations.LISTSCREEN

class NavigationActions(navController: NavHostController) {

    val coverScreen: () -> Unit = { navController.navigate(COVERSCREEN) }

    val jokeListScreen: () -> Unit = {
        navController.navigate(LISTSCREEN)
    }
    val punchLineScreen: (String) -> Unit = { punchlineText ->
        navController.navigate("$CHILDSCREEN/$punchlineText")
    }
    val navigateBack: () -> Unit = {
        navController.navigateUp()
    }
}

object MainDestinations {
    const val COVERSCREEN = "coverscreen"
    const val LISTSCREEN = "listscreen"
    const val CHILDSCREEN = "childscreen"
    const val CHILD_TITLE_KEY = "childscreen_titletkey"
}
