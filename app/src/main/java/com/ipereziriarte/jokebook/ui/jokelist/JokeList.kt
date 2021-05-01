package com.ipereziriarte.jokebook.ui.jokelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ipereziriarte.data.Joke
import com.ipereziriarte.data.datasources.remote.jokes.CallResult
import com.ipereziriarte.jokebook.ui.components.JokeListItem
import com.ipereziriarte.jokebook.ui.components.LoadingCircleAnimation
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LoadingCircleAnimation()
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
        val scrollState = rememberLazyListState()
        LazyColumn(state = scrollState) {
            items(jokes) { joke ->
                JokeListItem(actions = actions, jokeSetup = joke.setup, jokePunchLine = joke.punchLine)
            }
        }
    }
}
