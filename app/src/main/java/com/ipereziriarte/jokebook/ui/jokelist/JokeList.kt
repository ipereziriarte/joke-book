package com.ipereziriarte.jokebook.ui.jokelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ipereziriarte.data.Joke
import com.ipereziriarte.data.datasources.remote.jokes.CallResult
import com.ipereziriarte.jokebook.ui.home.NavigationActions
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun JokeList(actions: NavigationActions) {
    val allTabs = JokeTab.values().toList()
    val pagerState = rememberPagerState(pageCount = allTabs.size)
    val coroutineScope = rememberCoroutineScope()
    Surface(color = MaterialTheme.colors.surface) {
        Column {
            Text(text = "This is the joke list")
            Spacer(modifier = Modifier.size(32.dp))

            TabRow(selectedTabIndex = pagerState.currentPage) {

                allTabs.forEachIndexed { index, tab ->
                    Tab(
                        text = { Text(text = tab.name) },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                when (page) {
                    JokeTab.General.ordinal -> GeneralBody(actions)
                    JokeTab.Knock.ordinal -> KnockBody(actions)
                    JokeTab.Programming.ordinal -> ProgrammingBody(actions)
                }
            }

            Spacer(modifier = Modifier.size(32.dp))
            Button(
                onClick = { actions.punchLineScreen("See the punchline") }, modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = "See the punchline")
            }
        }
    }
}

enum class JokeTab {
    General,
    Knock,
    Programming
}

@Composable
fun GeneralBody(actions: NavigationActions) {
    GeneralBody(generalViewModel = hiltNavGraphViewModel(), actions = actions)
}

@Suppress("UNCHECKED_CAST")
@Composable
fun GeneralBody(generalViewModel: JokeListGeneralViewModel, actions: NavigationActions) {

    val result: CallResult by generalViewModel.fetchJokes.observeAsState(CallResult.Success(emptyList()))
    when (result) {
        is CallResult.Loading -> ShowLoading()
        is CallResult.Success -> ShowBody(jokes = (result as CallResult.Success).data, actions = actions)
        is CallResult.Failure -> ShowError()
    }
}

@Composable
fun KnockBody(actions: NavigationActions) {
    KnockBody(knockViewModel = hiltNavGraphViewModel(), actions = actions)
}

@Composable
fun KnockBody(knockViewModel: JokeListKnockViewModel, actions: NavigationActions) {
    val result: CallResult by knockViewModel.fetchJokes.observeAsState(CallResult.Success(emptyList()))
    when (result) {
        is CallResult.Loading -> ShowLoading()
        is CallResult.Success -> ShowBody(jokes = (result as CallResult.Success).data, actions = actions)
        is CallResult.Failure -> ShowError()
    }
}

@Composable
fun ProgrammingBody(actions: NavigationActions) {
    ProgrammingBody(programmingViewModel = hiltNavGraphViewModel(), actions = actions)
}

@Composable
fun ProgrammingBody(programmingViewModel: JokeListProgrammingViewModel, actions: NavigationActions) {
    val result: CallResult by programmingViewModel.fetchJokes.observeAsState(CallResult.Success(emptyList()))
    when (result) {
        is CallResult.Loading -> ShowLoading()
        is CallResult.Success -> ShowBody(jokes = (result as CallResult.Success).data, actions = actions)
        is CallResult.Failure -> ShowError()
    }
}

@Composable
fun ShowLoading() {
    Box(Modifier.fillMaxSize()) {
        Text("Loading")
    }
}

@Composable
fun ShowError() {
    Box(Modifier.fillMaxSize()) {
        Text("Error loading data")
    }
}

@Composable
fun ShowBody(jokes: List<Joke>, actions: NavigationActions) {
    Box(Modifier.fillMaxSize()) {
        Column() {
            if (jokes.isEmpty()) {
                Text(text = "No available jokes")
            } else {
                jokes.forEach { joke ->
                    Row(Modifier.clickable(onClick = { actions.punchLineScreen(joke.punchLine) })) {
                        Text(text = joke.setup)
                    }
                }
            }
        }
    }
}
