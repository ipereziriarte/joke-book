package com.ipereziriarte.jokebook.ui.jokelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun JokeList(navigateToPunchLine: () -> Unit) {
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
                    JokeTab.General.ordinal -> GeneralBody()
                    JokeTab.Knock.ordinal -> KnockBody()
                    JokeTab.Programming.ordinal -> ProgrammingBody()
                }
            }

            Spacer(modifier = Modifier.size(32.dp))
            Button(
                onClick = { navigateToPunchLine() }, modifier = Modifier.padding(8.dp),
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
fun GeneralBody() {
    Box(Modifier.fillMaxSize()) {
        Text(text = "Tab 1 content")
    }
}

@Composable
fun KnockBody() {
    Box(Modifier.fillMaxSize()) {
        Text(text = "Tab 2 content")
    }
}

@Composable
fun ProgrammingBody() {
    Box(Modifier.fillMaxSize()) {
        Text(text = "Tab 3 content")
    }
}
