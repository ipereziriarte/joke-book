package com.ipereziriarte.jokebook.ui.cover

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ipereziriarte.jokebook.R
import com.ipereziriarte.jokebook.ui.home.NavigationActions

@Composable
internal fun Cover(actions: NavigationActions) {
    Surface(color = MaterialTheme.colors.surface) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.cover_title),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Image(
                painter = painterResource(id = R.drawable.risitas),
                contentDescription = "Jokes",
                modifier = Modifier
                    .height(180.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                onClick = { actions.jokeListScreen() },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
                    .testTag("CoverMainBtn"),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = stringResource(id = R.string.cover_button))
            }
        }
    }
}

@Preview
@Composable
fun CoverPreview() {
    val actions = NavigationActions(NavHostController(LocalContext.current))
    Cover(actions)
}
