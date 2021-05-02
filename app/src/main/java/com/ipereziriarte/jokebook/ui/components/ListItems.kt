package com.ipereziriarte.jokebook.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ipereziriarte.jokebook.ui.navigation.NavigationActions

@Composable
internal fun JokeListItem(jokeSetup: String = "Lolz", jokePunchLine: String = "haha", actions: NavigationActions) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = { actions.punchLineScreen(jokePunchLine) }),
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
    ) {
        Column {
            Text(
                text = jokeSetup,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}
