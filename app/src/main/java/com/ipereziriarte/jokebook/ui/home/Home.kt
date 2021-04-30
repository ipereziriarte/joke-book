package com.ipereziriarte.jokebook.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.ipereziriarte.jokebook.ui.cover.Cover
import com.ipereziriarte.jokebook.ui.jokelist.JokeList
import com.ipereziriarte.jokebook.ui.navigation.Screen
import com.ipereziriarte.jokebook.ui.punchline.Punchline
import com.ipereziriarte.jokebook.ui.theme.JokeBookTheme

@Composable
internal fun Home() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            Cover { navController.navigate(Screen.JokeList.route) }
        }
        composable(Screen.JokeList.route) {
            JokeList { navController.navigate(Screen.PunchLine.route) }
        }
        composable(Screen.PunchLine.route) {
            Punchline { navController.popBackStack() }
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
