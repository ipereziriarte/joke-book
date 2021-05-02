package com.ipereziriarte.jokebook.ui.punchline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.ipereziriarte.jokebook.ui.navigation.NavigationActions
import com.ipereziriarte.jokebook.ui.theme.Teal200

@Composable
internal fun Punchline(actions: NavigationActions, punchline: String = "") {

    PunchlineCard(actions, punchline)
}

@Composable
internal fun PunchlineCard(actions: NavigationActions, punchline: String = "") {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = { actions.navigateBack() }),
            shape = RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp),
            backgroundColor = Teal200,
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp).testTag("PunchLineText"), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = punchline, style = MaterialTheme.typography.h2,)
            }
        }
    }
}
